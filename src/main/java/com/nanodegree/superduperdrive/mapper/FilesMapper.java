package com.nanodegree.superduperdrive.mapper;

import com.nanodegree.superduperdrive.model.Files;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FilesMapper {

    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    Files getFiles(String filename);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    Files getFilesById(Integer fileId);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    Files[] getFilesByUser(Integer userId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true,keyProperty = "fileId")
    int insertFiles(Files files);

    @Delete("DELETE FROM FILES WHERE filename = #{fileName}")
    void deleteFiles(String fileName);
}
