package com.nanodegree.superduperdrive.services;

import com.nanodegree.superduperdrive.mapper.NotesMapper;
import com.nanodegree.superduperdrive.mapper.UserMapper;
import com.nanodegree.superduperdrive.model.Notes;
import org.springframework.stereotype.Service;

@Service
public class NotesService {

    private final NotesMapper notesMapper;
    private final UserMapper userMapper;

    public NotesService(NotesMapper notesMapper, UserMapper userMapper) {
        this.notesMapper = notesMapper;
        this.userMapper = userMapper;
    }

    public Notes[] getNotes(String noteTitle) {
        return notesMapper.getNotes(noteTitle);
    }

    public Notes getNotesById(Integer noteId) {
        return notesMapper.getNotesById(noteId);
    }

    public Notes[] getNotesByUserName(String username) {
        Integer userId = userMapper.getUser(username).getUserId();
        return notesMapper.getNotesByUser(userId);
    }
    public Notes[] getNotesByUser(Integer userId) {
        return notesMapper.getNotesByUser(userId);
    }

    public void addNotes(String username, String noteTitle, String noteDescription) {
        Integer userId = userMapper.getUser(username).getUserId();
        notesMapper.insertNotes(new Notes(0,noteTitle,noteDescription,userId));
    }

    public void updateNotes(Integer noteId, String newTitle, String newDesc) {
        notesMapper.updateNote(noteId,newTitle,newDesc);
    }

    public void deleteNotes(Integer noteId) {
        notesMapper.deleteNoteById(noteId);
    }
}
