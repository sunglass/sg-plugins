package com.sg.utils;

/*
 * Alias .obj loader for processing
 * programmed by Tatsuya SAITO / UCLA Design | Media Arts 
 * Created on 2005/04/17
 *
 * 
 *  
 */

import processing.core.PImage;

/**
 * @author tatsuyas
 * 
 */
public class Material {

	public PImage map_Kd;
	public float[] Ka;
	public float[] Kd;
	public float[] Ks;
	public float d;

	public String mtlName;

	/**
	 * Constructs a default Material object.
	 */
	public Material() {
		Ka = new float[4];
		Kd = new float[4];
		Ks = new float[4];

		for (int i = 0; i < Ka.length; i++) {
			if (i == 3) {
				Ka[i] = 1f;
				Kd[i] = 1f;
				Ks[i] = 1f;
			} else {
				Ka[i] = 0.5f;
				Kd[i] = 0.5f;
				Ks[i] = 0.5f;
			}
		}

		d = 1.0f;

		mtlName = "default";
	}
	
	protected int[] tex = { 0 };
}
