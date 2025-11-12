<template>
  <div class="resource-recommendation-container">
    <!-- 页面标题 / Page Title -->
    <div class="page-header">
      <h2>
        <i class="el-icon-magic-stick"></i>
        智能学习资源推荐 / Intelligent Resource Recommendation
      </h2>
      <p class="subtitle">
        基于AI算法的个性化学习资源推荐系统 / AI-powered personalized learning
        resource recommendations
      </p>
    </div>

    <!-- 推荐参数设置 / Recommendation Settings -->
    <el-card class="settings-card">
      <div slot="header" class="card-header">
        <span><i class="el-icon-setting"></i> 推荐参数设置 / Settings</span>
      </div>

      <el-form :model="recommendForm" label-width="120px" size="medium">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="科目 / Subject">
              <el-select
                v-model="recommendForm.subjectId"
                placeholder="请选择科目 / Select subject"
                style="width: 100%"
              >
                <el-option
                  v-for="subject in subjects"
                  :key="subject.id"
                  :label="subject.name"
                  :value="subject.id"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="推荐算法 / Algorithm">
              <el-select
                v-model="recommendForm.algorithm"
                placeholder="选择算法 / Select algorithm"
                style="width: 100%"
              >
                <el-option
                  v-for="(algo, key) in ALGORITHM_TYPES"
                  :key="key"
                  :label="algo.label"
                  :value="algo.value"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="资源类型 / Type">
              <el-select
                v-model="recommendForm.resourceType"
                placeholder="全部类型 / All types"
                clearable
                style="width: 100%"
              >
                <el-option
                  v-for="(type, key) in RESOURCE_TYPES"
                  :key="key"
                  :label="type.label"
                  :value="type.value"
                >
                  <i :class="`el-icon-${type.icon}`"></i> {{ type.label }}
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="难度级别 / Difficulty">
              <el-select
                v-model="recommendForm.difficultyLevel"
                placeholder="全部难度 / All levels"
                clearable
                style="width: 100%"
              >
                <el-option
                  v-for="(level, key) in DIFFICULTY_LEVELS"
                  :key="key"
                  :label="level.label"
                  :value="level.value"
                  :style="{ color: level.color }"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="推荐数量 / Limit">
              <el-input-number
                v-model="recommendForm.limit"
                :min="1"
                :max="20"
                style="width: 100%"
              ></el-input-number>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="只看未学 / Unlearned">
              <el-switch v-model="recommendForm.unlearnedOnly"></el-switch>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item>
          <el-button
            type="primary"
            icon="el-icon-search"
            @click="fetchRecommendations"
            :loading="loading"
          >
            获取推荐 / Get Recommendations
          </el-button>
          <el-button icon="el-icon-refresh" @click="resetForm"
            >重置 / Reset</el-button
          >
          <el-button icon="el-icon-data-analysis" @click="showWeakPoints"
            >分析薄弱点 / Analyze Weak Points</el-button
          >
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 推荐结果展示 / Recommendation Results -->
    <el-card class="results-card" shadow="hover" v-loading="loading">
      <div slot="header" class="card-header">
        <span
          ><i class="el-icon-collection-tag"></i> 推荐结果 / Recommendations ({{
            recommendations.length
          }})</span
        >
        <el-tag v-if="recommendations.length > 0" type="success" size="small">
          置信度最高:
          {{ (recommendations[0].confidenceScore * 100).toFixed(1) }}%
        </el-tag>
      </div>

      <!-- 空状态 / Empty State -->
      <el-empty
        v-if="recommendations.length === 0 && !loading"
        description="暂无推荐，请设置参数并点击获取推荐 / No recommendations yet"
      >
        <el-button type="primary" @click="fetchRecommendations"
          >获取推荐 / Get Recommendations</el-button
        >
      </el-empty>

      <!-- 推荐列表 / Recommendation List -->
      <div class="recommendation-list" v-else>
        <el-card
          v-for="(item, index) in recommendations"
          :key="item.resourceId"
          class="recommendation-item"
          :body-style="{ padding: '20px' }"
          shadow="hover"
        >
          <!-- 排名标识 / Rank Badge -->
          <el-tag
            class="rank-badge"
            :type="getRankType(index + 1)"
            size="medium"
          >
            #{{ index + 1 }}
          </el-tag>

          <div class="item-content">
            <!-- 资源标题和类型 / Resource Title and Type -->
            <div class="item-header">
              <h3>
                <i :class="getResourceIcon(item.resourceType)"></i>
                {{ item.resourceName }}
              </h3>
              <el-tag
                :type="getDifficultyType(item.difficultyLevel)"
                size="small"
              >
                {{ item.difficultyLevel }}
              </el-tag>
            </div>

            <!-- 资源描述 / Resource Description -->
            <p class="item-description">
              {{ item.description || "暂无描述 / No description" }}
            </p>

            <!-- 推荐理由 / Recommendation Reason -->
            <div class="recommendation-reason">
              <el-alert
                :title="item.recommendationReason"
                type="info"
                :closable="false"
                show-icon
              >
              </el-alert>
            </div>

            <!-- 资源信息 / Resource Info -->
            <div class="item-info">
              <el-descriptions :column="4" size="small" border>
                <el-descriptions-item label="推荐置信度 / Confidence">
                  <el-progress
                    :percentage="
                      Number((item.confidenceScore * 100).toFixed(1))
                    "
                    :color="getConfidenceColor(item.confidenceScore)"
                    :stroke-width="10"
                  >
                  </el-progress>
                </el-descriptions-item>
                <el-descriptions-item label="学习时长 / Duration">
                  <i class="el-icon-time"></i> {{ item.durationMinutes }} 分钟 /
                  min
                </el-descriptions-item>
                <el-descriptions-item label="有效性评分 / Effectiveness">
                  <el-rate
                    v-model="item.effectivenessScore"
                    disabled
                    show-score
                    text-color="#ff9900"
                    score-template="{value}"
                  >
                  </el-rate>
                </el-descriptions-item>
                <el-descriptions-item label="算法 / Algorithm">
                  <el-tag size="mini">{{ item.algorithm }}</el-tag>
                </el-descriptions-item>
              </el-descriptions>
            </div>

            <!-- 操作按钮 / Action Buttons -->
            <div class="item-actions">
              <el-button
                type="primary"
                size="small"
                @click="startLearning(item)"
              >
                <i class="el-icon-video-play"></i> 开始学习 / Start Learning
              </el-button>
              <el-button type="success" size="small" @click="viewDetails(item)">
                <i class="el-icon-view"></i> 查看详情 / View Details
              </el-button>
              <el-button
                type="warning"
                size="small"
                @click="submitFeedback(item)"
              >
                <i class="el-icon-star-off"></i> 评价 / Rate
              </el-button>
            </div>
          </div>
        </el-card>
      </div>
    </el-card>

    <!-- 薄弱知识点分析对话框 / Weak Points Analysis Dialog -->
    <el-dialog
      title="薄弱知识点分析 / Weak Knowledge Points Analysis"
      :visible.sync="weakPointsDialogVisible"
      width="600px"
    >
      <el-table :data="weakPointsData" style="width: 100%">
        <el-table-column
          prop="knowledgePointId"
          label="知识点ID / KP ID"
          width="100"
        ></el-table-column>
        <el-table-column
          prop="knowledgePointName"
          label="知识点名称 / Name"
        ></el-table-column>
        <el-table-column label="掌握度 / Mastery" width="200">
          <template slot-scope="scope">
            <el-progress
              :percentage="Number((scope.row.masteryLevel * 100).toFixed(1))"
              :color="getMasteryColor(scope.row.masteryLevel)"
              :format="(format) => `${format}%`"
            >
            </el-progress>
          </template>
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="weakPointsDialogVisible = false"
          >关闭 / Close</el-button
        >
      </span>
    </el-dialog>

    <!-- 反馈对话框 / Feedback Dialog -->
    <el-dialog
      title="提交反馈 / Submit Feedback"
      :visible.sync="feedbackDialogVisible"
      width="500px"
    >
      <el-form :model="feedbackForm" label-width="100px">
        <el-form-item label="评分 / Score">
          <el-rate v-model="feedbackForm.score" show-text></el-rate>
        </el-form-item>
        <el-form-item label="评论 / Comment">
          <el-input
            type="textarea"
            v-model="feedbackForm.comment"
            :rows="4"
            placeholder="请输入您的评价... / Please enter your feedback..."
          >
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="feedbackDialogVisible = false"
          >取消 / Cancel</el-button
        >
        <el-button type="primary" @click="confirmFeedback"
          >提交 / Submit</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  getRecommendations,
  analyzeWeakPoints,
  submitFeedback,
  ALGORITHM_TYPES,
  RESOURCE_TYPES,
  DIFFICULTY_LEVELS,
} from "@/api/recommendation";

export default {
  name: "ResourceRecommendation",

  data() {
    return {
      // 常量 / Constants
      ALGORITHM_TYPES,
      RESOURCE_TYPES,
      DIFFICULTY_LEVELS,

      // 推荐表单 / Recommendation Form
      recommendForm: {
        studentId: null,
        subjectId: null,
        algorithm: "AI",
        resourceType: null,
        difficultyLevel: null,
        limit: 5,
        unlearnedOnly: false,
        minEffectivenessScore: 0.0,
      },

      // 科目列表 / Subject List
      subjects: [
        { id: 1, name: "数学 / Mathematics" },
        { id: 2, name: "英语 / English" },
        { id: 3, name: "物理 / Physics" },
        { id: 4, name: "化学 / Chemistry" },
        { id: 5, name: "生物 / Biology" },
      ],

      // 推荐结果 / Recommendations
      recommendations: [],
      loading: false,

      // 薄弱知识点 / Weak Points
      weakPointsDialogVisible: false,
      weakPointsData: [],

      // 反馈 / Feedback
      feedbackDialogVisible: false,
      feedbackForm: {
        recommendationId: null,
        score: 5,
        comment: "",
      },
    };
  },

  created() {
    // 获取当前登录学生ID / Get current student ID
    this.recommendForm.studentId = this.$store.getters.userId || 1;
  },

  methods: {
    /**
     * 获取推荐 / Fetch recommendations
     */
    async fetchRecommendations() {
      if (!this.recommendForm.subjectId) {
        this.$message.warning("请先选择科目 / Please select a subject first");
        return;
      }

      this.loading = true;
      try {
        const response = await getRecommendations(this.recommendForm);

        if (response.code === 200) {
          this.recommendations = response.data || [];
          this.$message.success(
            `成功获取${this.recommendations.length}条推荐 / Got ${this.recommendations.length} recommendations`
          );
        } else {
          this.$message.error(
            response.message || "获取推荐失败 / Failed to get recommendations"
          );
        }
      } catch (error) {
        console.error("获取推荐失败 / Failed to fetch recommendations:", error);
        this.$message.error(
          "网络错误，请稍后重试 / Network error, please try again"
        );
      } finally {
        this.loading = false;
      }
    },

    /**
     * 显示薄弱知识点 / Show weak knowledge points
     */
    async showWeakPoints() {
      if (!this.recommendForm.subjectId) {
        this.$message.warning("请先选择科目 / Please select a subject first");
        return;
      }

      try {
        const response = await analyzeWeakPoints(
          this.recommendForm.studentId,
          this.recommendForm.subjectId
        );

        if (response.code === 200) {
          const weakPoints = response.data || {};
          this.weakPointsData = Object.entries(weakPoints).map(
            ([id, mastery]) => ({
              knowledgePointId: id,
              knowledgePointName: `知识点 ${id} / Knowledge Point ${id}`,
              masteryLevel: mastery,
            })
          );
          this.weakPointsDialogVisible = true;
        }
      } catch (error) {
        console.error("分析薄弱点失败 / Failed to analyze weak points:", error);
        this.$message.error("分析失败 / Analysis failed");
      }
    },

    /**
     * 重置表单 / Reset form
     */
    resetForm() {
      this.recommendForm = {
        ...this.recommendForm,
        resourceType: null,
        difficultyLevel: null,
        limit: 5,
        unlearnedOnly: false,
      };
      this.recommendations = [];
    },

    /**
     * 开始学习 / Start learning
     */
    startLearning(item) {
      this.$message.success(
        `开始学习: ${item.resourceName} / Starting to learn: ${item.resourceName}`
      );
      // 跳转到学习页面或打开资源链接
      if (item.resourceUrl) {
        window.open(item.resourceUrl, "_blank");
      }
    },

    /**
     * 查看详情 / View details
     */
    viewDetails(item) {
      this.$router.push({
        name: "ResourceDetail",
        params: { id: item.resourceId },
      });
    },

    /**
     * 提交反馈 / Submit feedback
     */
    submitFeedback(item) {
      this.feedbackForm.recommendationId = item.recommendationId;
      this.feedbackForm.score = 5;
      this.feedbackForm.comment = "";
      this.feedbackDialogVisible = true;
    },

    /**
     * 确认反馈 / Confirm feedback
     */
    async confirmFeedback() {
      try {
        const response = await submitFeedback(
          this.feedbackForm.recommendationId,
          this.feedbackForm.score,
          this.feedbackForm.comment
        );

        if (response.code === 200) {
          this.$message.success(
            "反馈提交成功 / Feedback submitted successfully"
          );
          this.feedbackDialogVisible = false;
        }
      } catch (error) {
        console.error("提交反馈失败 / Failed to submit feedback:", error);
        this.$message.error("提交失败 / Submission failed");
      }
    },

    /**
     * 获取排名类型 / Get rank type
     */
    getRankType(rank) {
      if (rank === 1) return "danger";
      if (rank <= 3) return "warning";
      return "info";
    },

    /**
     * 获取资源图标 / Get resource icon
     */
    getResourceIcon(type) {
      const icons = {
        VIDEO: "el-icon-video-camera",
        DOCUMENT: "el-icon-file-text",
        EXERCISE: "el-icon-edit",
        ARTICLE: "el-icon-reading",
        INTERACTIVE: "el-icon-menu",
      };
      return icons[type] || "el-icon-document";
    },

    /**
     * 获取难度类型 / Get difficulty type
     */
    getDifficultyType(level) {
      const types = {
        EASY: "success",
        MEDIUM: "primary",
        HARD: "danger",
      };
      return types[level] || "info";
    },

    /**
     * 获取置信度颜色 / Get confidence color
     */
    getConfidenceColor(confidence) {
      if (confidence >= 0.8) return "#67c23a";
      if (confidence >= 0.6) return "#e6a23c";
      return "#f56c6c";
    },

    /**
     * 获取掌握度颜色 / Get mastery color
     */
    getMasteryColor(mastery) {
      if (mastery >= 0.7) return "#67c23a";
      if (mastery >= 0.4) return "#e6a23c";
      return "#f56c6c";
    },
  },
};
</script>

<style scoped lang="scss">
.resource-recommendation-container {
  padding: 20px;
  background-color: #f5f7fa;
  min-height: 100vh;
}

.page-header {
  text-align: center;
  margin-bottom: 30px;

  h2 {
    font-size: 28px;
    color: #303133;
    margin-bottom: 10px;

    i {
      color: #409eff;
      margin-right: 10px;
    }
  }

  .subtitle {
    color: #909399;
    font-size: 14px;
  }
}

.settings-card,
.results-card {
  margin-bottom: 20px;

  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-weight: bold;

    i {
      margin-right: 8px;
      color: #409eff;
    }
  }
}

.recommendation-list {
  display: grid;
  gap: 20px;
}

.recommendation-item {
  position: relative;
  transition: all 0.3s ease;
  background-color: #fff;

  &:hover {
    transform: translateY(-2px);
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
  }

  .rank-badge {
    position: absolute;
    top: 10px;
    right: 10px;
    font-weight: bold;
    font-size: 16px;
    padding: 8px 12px;
  }

  .item-content {
    .item-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 15px;

      h3 {
        font-size: 20px;
        color: #303133;
        margin: 0;
        flex: 1;

        i {
          color: #409eff;
          margin-right: 8px;
        }
      }
    }

    .item-description {
      color: #606266;
      font-size: 14px;
      line-height: 1.6;
      margin-bottom: 15px;
    }

    .recommendation-reason {
      margin-bottom: 20px;
    }

    .item-info {
      margin-bottom: 20px;
    }

    .item-actions {
      display: flex;
      gap: 10px;
      justify-content: flex-end;
    }
  }
}

// 响应式设计 / Responsive Design
@media (max-width: 768px) {
  .recommendation-item {
    .item-actions {
      flex-direction: column;

      .el-button {
        width: 100%;
      }
    }
  }
}
</style>
