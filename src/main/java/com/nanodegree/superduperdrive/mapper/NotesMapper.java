package com.nanodegree.superduperdrive.mapper;

import com.nanodegree.superduperdrive.model.Files;
import com.nanodegree.superduperdrive.model.Notes;
import org.apache.ibatis.annotations.*;

/**
 * The interface Notes mapper.
 */
@Mapper
public interface NotesMapper {

    /**
     * Get notes notes [ ].
     *
     * @param noteTitle the note title
     * @return the notes [ ]
     */
    @Select("SELECT * FROM NOTES WHERE notetitle = #{noteTitle}")
    Notes[] getNotes(String noteTitle);

    /**
     * Gets notes by id.
     *
     * @param noteId the note id
     * @return the notes by id
     */
    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    Notes getNotesById(Integer noteId);

    /**
     * Get notes by user notes [ ].
     *
     * @param userId the user id
     * @return the notes [ ]
     */
    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    Notes[] getNotesByUser(Integer userId);

    /**
     * Get all notes notes [ ].
     *
     * @return the notes [ ]
     */
    @Select("SELECT * FROM NOTES")
    Notes[] getAllNotes();

    /**
     * Insert notes int.
     *
     * @param notes the notes
     * @return the int
     */
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true,keyProperty = "noteId")
    int insertNotes(Notes notes);

    /**
     * Delete notes.
     *
     * @param noteTitle the note title
     */
    @Delete("DELETE FROM NOTES WHERE notetitle = #{noteTitle}")
    void deleteNotes(String noteTitle);

    /**
     * Delete note by id.
     *
     * @param noteId the note id
     */
    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteNoteById(Integer noteId);

    /**
     * Update note.
     *
     * @param noteId         the note id
     * @param newTitle       the new title
     * @param newDescription the new description
     */
    @Update("UPDATE NOTES SET notetitle = #{newTitle}, notedescription = #{newDescription} WHERE noteid = #{noteId}")
    void updateNote(Integer noteId, String newTitle, String newDescription);
}
