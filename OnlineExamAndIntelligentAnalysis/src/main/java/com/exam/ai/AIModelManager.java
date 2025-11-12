package com.exam.ai;

import com.exam.exception.ExamException;
import com.exam.utils.result.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 模型运行时调度器 // Runtime dispatcher for AI models
 */
@Service
public class AIModelManager {

    private final Map<String, AIModelAdapter> adapterMap; // 模型适配器映射 // Adapter registry
    private final AtomicReference<String> activeModelCode; // 当前激活模型 // Active model reference

    public AIModelManager(List<AIModelAdapter> adapters,
                          @Value("${ai.model.default:openai}") String defaultModel) {
        if (CollectionUtils.isEmpty(adapters)) {
            throw new ExamException(ResultCodeEnum.FAIL); // 至少需要一个可用模型 // Require at least one adapter
        }
        this.adapterMap = Collections.unmodifiableMap(
                adapters.stream().collect(Collectors.toMap(
                        AIModelAdapter::getName,
                        Function.identity(),
                        (left, right) -> left,
                        LinkedHashMap::new // 保留声明顺序 // Keep declaration order
                )));
        String resolvedDefault = adapterMap.containsKey(defaultModel)
                ? defaultModel
                : adapters.get(0).getName(); // 如果配置不存在则取第一个 // fallback to first adapter
        this.activeModelCode = new AtomicReference<>(resolvedDefault);
    }

    /**
     * 解析模型，如果未指定则返回激活模型 // Resolve adapter or fallback to active model
     */
    public AIModelAdapter resolveAdapter(String preferredModelCode) {
        if (StringUtils.hasText(preferredModelCode) && adapterMap.containsKey(preferredModelCode)) {
            return adapterMap.get(preferredModelCode);
        }
        return adapterMap.getOrDefault(activeModelCode.get(), adapterMap.values().iterator().next());
    }

    /**
     * 更新激活模型 // Switch active model
     */
    public void switchActiveModel(String modelCode) {
        if (!adapterMap.containsKey(modelCode)) {
            throw new ExamException(ResultCodeEnum.PARAM_ERROR); // 非法模型编码 // Illegal model code
        }
        activeModelCode.set(modelCode);
    }

    /**
     * 获取模型描述 // Expose model descriptors
     */
    public List<AIModelDescriptor> describeModels() {
        String current = activeModelCode.get();
        return adapterMap.values().stream()
                .map(adapter -> new AIModelDescriptor(
                        adapter.getName(),
                        adapter.getDisplayName(),
                        adapter.supportsFunctionCalling(),
                        adapter.isHealthy(),
                        adapter.getName().equals(current)
                ))
                .toList();
    }

    /**
     * 当前激活模型编码 // Active model code getter
     */
    public String getActiveModelCode() {
        return activeModelCode.get();
    }
}
