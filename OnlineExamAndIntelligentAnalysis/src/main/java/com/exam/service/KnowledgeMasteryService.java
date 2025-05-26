package com.exam.service;

import weka.classifiers.trees.J48;
import weka.core.Instances;

public interface KnowledgeMasteryService {

    // 加载数据集
    Instances loadDataset(String filePath) throws Exception;

    // 构建决策树模型
    J48 buildDecisionTree(Instances dataset) throws Exception;

    // 评估模型的性能
    void evaluateModel(J48 model, Instances dataset) throws Exception;

    // 对新的数据进行预测
    String predictMasteryLevel(J48 model, Instances dataset,  double totalQuestions, double wrongQuestions, double scoreRate) throws Exception;
    // 对外提供的分析接口
    public String analyzeMasteryLevel(double totalQuestions, double scoreRate, double wrongRate) throws Exception;
}
