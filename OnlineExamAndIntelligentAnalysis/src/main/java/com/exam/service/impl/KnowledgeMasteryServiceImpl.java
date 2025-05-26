package com.exam.service.impl;

import com.exam.service.KnowledgeMasteryService;
import org.springframework.stereotype.Service;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.*;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.util.Random;

/**
 * Author:CM
 * Date: 2025/3/1 13:48
 */
@Service
public class KnowledgeMasteryServiceImpl implements KnowledgeMasteryService {
    private J48 decisionTreeModel;
    private Instances dataset; // 保留数据集结构

    public KnowledgeMasteryServiceImpl() throws Exception {
        // 初始化数据集和决策树模型
        this.dataset = loadDataset("d:/dataset.csv");
        // 如果存在d:/tree_model.model文件，则加载模型；否则，训练模型并保存
        if (new File("d:/tree_model.model").exists()) {
            this.decisionTreeModel = loadDecisionTree("d:/tree_model.model");
        } else {
            this.decisionTreeModel = buildDecisionTree(dataset);
            evaluateModel(decisionTreeModel, dataset);
        }
    }

    @Override
    public String analyzeMasteryLevel(double totalQuestions, double scoreRate, double wrongRate) throws Exception {
        return predictMasteryLevel(decisionTreeModel, dataset, totalQuestions, scoreRate, wrongRate);
    }

    // 加载数据集
    @Override
    public Instances loadDataset(String filePath) throws Exception {
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(filePath));
        Instances dataset = loader.getDataSet();

        // 设置目标变量（假设最后一列是目标变量）
        dataset.setClassIndex(dataset.numAttributes() - 1);
        // 打印数据集信息，用于调试
//        System.out.println("Dataset loaded successfully:");
//        System.out.println("Number of attributes: " + dataset.numAttributes());
//        System.out.println("Number of instances: " + dataset.numInstances());
//        System.out.println("Class attribute: " + dataset.classAttribute());
//        System.out.println("Class values: " + dataset.classAttribute().numValues());
//        for (int i = 0; i < dataset.classAttribute().numValues(); i++) {
//            System.out.println("Class value " + i + ": " + dataset.classAttribute().value(i));
//        }

        return dataset;
    }

    // 构建决策树模型
    @Override
    public J48 buildDecisionTree(Instances dataset) throws Exception {
        dataset.setClassIndex(dataset.numAttributes() - 1); // 设置目标变量（最后一列）
        J48 tree = new J48();
        tree.buildClassifier(dataset); // 训练模型
        // 保存模型到文件
        String modelPath = "d:/tree_model.model"; // 模型保存路径
        SerializationHelper.write(modelPath, tree); // 保存模型
        System.out.println("Model saved to: " + modelPath);
        return tree;
    }

    // 加载决策树模型
    public J48 loadDecisionTree(String modelPath) throws Exception {
        // 从文件加载模型
        J48 tree = (J48) SerializationHelper.read(modelPath);
        System.out.println("Model loaded from: " + modelPath);
        return tree;
    }

    // 评估模型效果
    @Override
    public void evaluateModel(J48 model, Instances dataset) throws Exception {
        Evaluation eval = new Evaluation(dataset);
        eval.crossValidateModel(model, dataset, 10, new Random(1)); // 10 折交叉验证
        System.out.println(eval.toSummaryString()); // 输出评估结果
    }

    // 预测学员掌握知识水平
    @Override
    public String predictMasteryLevel(J48 model,Instances dataset, double totalQuestions, double scoreRate, double wrongRate) throws Exception {
        Instance instance = new DenseInstance(4); // 创建新实例
        instance.setValue(0, totalQuestions); // 设置累计答题数
        instance.setValue(1, scoreRate); // 设置得分率
        instance.setValue(2, wrongRate); // 设置错题率
        instance.setDataset(dataset); // 关联数据集

        double prediction = model.classifyInstance(instance); // 预测
        return dataset.classAttribute().value((int) prediction); // 返回预测结果
    }
}
