package com.sg.models;

import java.util.ArrayList;

public class NoteList {
	private ArrayList<Note> notes =	 new ArrayList<Note>();
	
	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	public void setNotes(ArrayList<Note> notes) {
		this.notes = notes;
	}
}
