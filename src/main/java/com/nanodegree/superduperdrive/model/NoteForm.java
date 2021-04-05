package com.nanodegree.superduperdrive.model;

/**
 * The type Note form.
 */
public class NoteForm {

    /**
     * Gets note id.
     *
     * @return the note id
     */
    public Integer getNoteId() {
        return noteId;
    }

    /**
     * Sets note id.
     *
     * @param noteId the note id
     */
    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    /**
     * Instantiates a new Note form.
     *
     * @param noteId          the note id
     * @param noteTitle       the note title
     * @param noteDescription the note description
     */
    public NoteForm(Integer noteId, String noteTitle, String noteDescription) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }

    /**
     * The Note id.
     */
    public Integer noteId;
    /**
     * The Note title.
     */
    public String noteTitle;
    /**
     * The Note description.
     */
    public String noteDescription;

    /**
     * Gets note title.
     *
     * @return the note title
     */
    public String getNoteTitle() {
        return noteTitle;
    }

    /**
     * Sets note title.
     *
     * @param noteTitle the note title
     */
    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    /**
     * Gets note description.
     *
     * @return the note description
     */
    public String getNoteDescription() {
        return noteDescription;
    }

    /**
     * Sets note description.
     *
     * @param noteDescription the note description
     */
    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

}
