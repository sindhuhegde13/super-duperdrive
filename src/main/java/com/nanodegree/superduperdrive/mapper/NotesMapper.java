package com.nanodegree.superduperdrive.mapper;

import com.nanodegree.superduperdrive.model.Files;
import com.nanodegree.superduperdrive.model.Notes;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NotesMapper {

    @Select("SELECT * FROM NOTES WHERE notetitle = #{noteTitle}")
    Notes[] getNotes(String noteTitle);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    Notes getNotesById(Integer noteId);

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    Notes[] getNotesByUser(Integer userId);

    @Select("SELECT * FROM NOTES")
    Notes[] getAllNotes();

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true,keyProperty = "noteId")
    int insertNotes(Notes notes);

    @Delete("DELETE FROM NOTES WHERE notetitle = #{noteTitle}")
    void deleteNotes(String noteTitle);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteNoteById(Integer noteId);

    @Update("UPDATE NOTES SET notetitle = #{newTitle}, notedescription = #{newDescription} WHERE noteid = #{noteId}")
    void updateNote(Integer noteId, String newTitle, String newDescription);
}
