package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.StudentLearningRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 学生学习记录Mapper接口
 * Student Learning Record Mapper Interface
 * 
 * 提供学生学习记录的数据库操作方法
 * Provides database operation methods for student learning records
 * 
 * @author AI Architect Team
 * @version 1.0.0
 * @since 2024-11-12
 */
@Mapper
public interface StudentLearningRecordMapper extends BaseMapper<StudentLearningRecord> {

    /**
     * 查询学生的学习记录 / Query student's learning records
     * @param studentId 学生ID / Student ID
     * @return 学习记录列表 / Learning record list
     */
    @Select("SELECT * FROM student_learning_record " +
            "WHERE student_id = #{studentId} " +
            "ORDER BY last_access_time DESC")
    List<StudentLearningRecord> selectByStudentId(@Param("studentId") Long studentId);

    /**
     * 查询学生的已完成学习记录 / Query student's completed learning records
     * @param studentId 学生ID / Student ID
     * @return 学习记录列表 / Learning record list
     */
    @Select("SELECT * FROM student_learning_record " +
            "WHERE student_id = #{studentId} AND is_completed = 1 " +
            "ORDER BY update_time DESC")
    List<StudentLearningRecord> selectCompletedByStudent(@Param("studentId") Long studentId);

    /**
     * 查询学生正在学习的资源 / Query student's ongoing learning resources
     * @param studentId 学生ID / Student ID
     * @return 学习记录列表 / Learning record list
     */
    @Select("SELECT * FROM student_learning_record " +
            "WHERE student_id = #{studentId} " +
            "AND is_completed = 0 " +
            "AND mastery_level = 'LEARNING' " +
            "ORDER BY last_access_time DESC")
    List<StudentLearningRecord> selectOngoingByStudent(@Param("studentId") Long studentId);

    /**
     * 查询特定资源的学习记录 / Query learning record for specific resource
     * @param studentId 学生ID / Student ID
     * @param resourceId 资源ID / Resource ID
     * @return 学习记录 / Learning record
     */
    @Select("SELECT * FROM student_learning_record " +
            "WHERE student_id = #{studentId} AND resource_id = #{resourceId}")
    StudentLearningRecord selectByStudentAndResource(@Param("studentId") Long studentId,
                                                     @Param("resourceId") Long resourceId);
}