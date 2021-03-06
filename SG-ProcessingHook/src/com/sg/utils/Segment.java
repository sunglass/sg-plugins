package com.sg.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.nio.*;

import javax.media.opengl.GL;

import processing.core.*;

public class Segment {
	public ArrayList<Face> faces;

	public String materialName;
	public IntBuffer indexIB;
	public FloatBuffer dataFB;

	int[] glbuf;

	/**
	 * Constructor for the ModelSegment, each Segment holds a Vector of
	 * Elements. each element is a collection of indexes to the vert, normal,
	 * and UV arrays that make up a single face.
	 */
	public Segment() {
		faces = new ArrayList<Face>();
	}

	public String getMaterialName() {
		return materialName;
	}

	public Face getFace(int index) {
		return faces.get(index);
	}

	public Face[] getFaces() {
		return faces.toArray(new Face[faces.size()]);
	}

	public PVector[] getIndices() {
		ArrayList<PVector> indices = new ArrayList<PVector>();

		for (int i = 0; i < faces.size(); i++)
			indices.addAll(Arrays.asList(getFace(i).getVertices()));

		return indices.toArray(new PVector[indices.size()]);
	}

	public int getFaceCount() {
		return faces.size();
	}

	public int getIndexCount() {
		int count = 0;

		for (int i = 0; i < getFaceCount(); i++)
			count += (getFace(i)).getVertIndexCount();

		return count;
	}

	public void sortFacesByX() {
		Collections.sort(faces, Face.FaceXComparator);
	}

	public void sortFacesByY() {
		Collections.sort(faces, Face.FaceYComparator);
	}

	public void sortFacesByZ() {
		Collections.sort(faces, Face.FaceZComparator);
	}

}
