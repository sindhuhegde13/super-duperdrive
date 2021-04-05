package com.nanodegree.superduperdrive.services;

import com.nanodegree.superduperdrive.mapper.NotesMapper;
import com.nanodegree.superduperdrive.mapper.UserMapper;
import com.nanodegree.superduperdrive.model.Notes;
import org.springframework.stereotype.Service;

/**
 * The type Notes service.
 */
@Service
public class NotesService {

    private final NotesMapper notesMapper;
    private final UserMapper userMapper;

    /**
     * Instantiates a new Notes service.
     *
     * @param notesMapper the notes mapper
     * @param userMapper  the user mapper
     */
    public NotesService(NotesMapper notesMapper, UserMapper userMapper) {
        this.notesMapper = notesMapper;
        this.userMapper = userMapper;
    }

    /**
     * Get notes notes [ ].
     *
     * @param noteTitle the note title
     * @return the notes [ ]
     */
    public Notes[] getNotes(String noteTitle) {
        return notesMapper.getNotes(noteTitle);
    }

    /**
     * Gets notes by id.
     *
     * @param noteId the note id
     * @return the notes by id
     */
    public Notes getNotesById(Integer noteId) {
        return notesMapper.getNotesById(noteId);
    }

    /**
     * Get notes by user name notes [ ].
     *
     * @param username the username
     * @return the notes [ ]
     */
    public Notes[] getNotesByUserName(String username) {
        Integer userId = userMapper.getUser(username).getUserId();
        return notesMapper.getNotesByUser(userId);
    }

    /**
     * Get notes by user notes [ ].
     *
     * @param userId the user id
     * @return the notes [ ]
     */
    public Notes[] getNotesByUser(Integer userId) {
        return notesMapper.getNotesByUser(userId);
    }

    /**
     * Add notes.
     *
     * @param username        the username
     * @param noteTitle       the note title
     * @param noteDescription the note description
     */
    public void addNotes(String username, String noteTitle, String noteDescription) {
        Integer userId = userMapper.getUser(username).getUserId();
        notesMapper.insertNotes(new Notes(0,noteTitle,noteDescription,userId));
    }

    /**
     * Update notes.
     *
     * @param noteId   the note id
     * @param newTitle the new title
     * @param newDesc  the new desc
     */
    public void updateNotes(Integer noteId, String newTitle, String newDesc) {
        notesMapper.updateNote(noteId,newTitle,newDesc);
    }

    /**
     * Delete notes.
     *
     * @param noteId the note id
     */
    public void deleteNotes(Integer noteId) {
        notesMapper.deleteNoteById(noteId);
    }
}
