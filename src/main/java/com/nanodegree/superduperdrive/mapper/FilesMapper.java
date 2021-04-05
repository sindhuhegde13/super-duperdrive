package com.nanodegree.superduperdrive.mapper;

import com.nanodegree.superduperdrive.model.Files;
import org.apache.ibatis.annotations.*;

/**
 * The interface Files mapper.
 */
@Mapper
public interface FilesMapper {

    /**
     * Gets files.
     *
     * @param filename the filename
     * @return the files
     */
    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    Files getFiles(String filename);

    /**
     * Gets files by id.
     *
     * @param fileId the file id
     * @return the files by id
     */
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    Files getFilesById(Integer fileId);

    /**
     * Get files by user files [ ].
     *
     * @param userId the user id
     * @return the files [ ]
     */
    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    Files[] getFilesByUser(Integer userId);

    /**
     * Insert files int.
     *
     * @param files the files
     * @return the int
     */
    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true,keyProperty = "fileId")
    int insertFiles(Files files);

    /**
     * Delete files.
     *
     * @param fileName the file name
     */
    @Delete("DELETE FROM FILES WHERE filename = #{fileName}")
    void deleteFiles(String fileName);
}
