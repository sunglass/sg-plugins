package com.sg;

/**
 * ##library.name##
 * ##library.sentence##
 * ##library.url##
 *
 * Copyright ##copyright## ##author##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author      ##author##
 * @modified    ##date##
 * @version     ##library.prettyVersion## (##library.version##)
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.sg.ConnectionManager;
import com.sg.json.*;

import com.sg.models.*;
import com.sg.utils.*;

import processing.core.*;

/**
 * SunglassConnect library will connect your Processing project to your Sunglass
 * repository on the cloud. Download / Upload / Manage your 3D data via HTTPS using the 
 * Sunglass REST API.
 * 
 * Currently we support OBJ, STL, 3DS, DAE files only. This is soon going to change!
 * 
 * To get started you should get your SID / TOKEN pair from your account/dashboard at https://sunglass.io
 * This will be required for authentication prior to getting any access to your repository.
 * 
 * @example sunglassConnectBasic
 */
public class SunglassConnect {

	public final static String VERSION = "SunglassConnect.0.1.1";
	/**
	 * Connection Manager which encapsulates all the gory details of
	 * the HTTP client methods.
	 */
	public ConnectionManager cm;
	
	/**
	 * The parent processing applet
	 */
	public PApplet myParent;

	//Constructor
	/**
	 * Constructor, usually called in the setup() method in your sketch to
	 * initialize and start the library.
	 * 
	 * @param theParent
	 *            PApplet
	 * @param sid
	 *           secure ID (unique to user - available at your Sunglass account)
	 * @param token
	 * 			 token (unique to user - available at your Sunglass account)
	 */
	public SunglassConnect(PApplet theParent, String sid, String token) {
		cm = new ConnectionManager(sid, token);
		myParent = theParent;
	}
	
	// User
	/**
	 * Constructor, usually called in the setup() method in your sketch to
	 * 
	 * @return int the user ID associated with the sid and token
	 */
	public int authenticate() throws Exception {
		return cm.getUserId();
	}

	// Project
	/**
	 * Create a Sunglass Project
	 * 
	 * @param String
	 *            project name
	 * @return Project 
	 * 			  the new sunglass project
	 * @throws JSONException
	 */
	public Project createProject(String projectName) throws JSONException {
		return cm.createProject(projectName);
	}

	/**
	 * Returns the list of projects in users Sunglass repository
	 * 
	 * @return HashMap<Integer, String> hashmap <project id, project name>
	 * @throws Exception
	 */
	public HashMap<Integer, String> getProjectNameList() throws Exception {
		HashMap<Integer, String> projectList = new HashMap<Integer, String>();
		ArrayList<Project> projs = cm.getProjects();
		for (Project p : projs)
			projectList.put(p.getId(), p.getName());
		return projectList;
	}

	/**
	 * Returns the list of projects in users Sunglass repository
	 * 
	 * @return HashMap<Integer, Project> hashmap <project id, project>
	 * @throws Exception
	 */
	public HashMap<Integer, Project> getProjectList() throws Exception {
		HashMap<Integer, Project> projectList = new HashMap<Integer, Project>();
		ArrayList<Project> projs = cm.getProjects();
		for (Project p : projs)
			projectList.put(p.getId(), p);
		return projectList;
	}

	// Space
	/**
	 * Returns the root space of a given Sunglass project
	 * 
	 * @param Project
	 *            a sunglass project
	 * @return Space the root space of the project
	 * @throws Exception
	 */
	public Space getProjectRootSpace(Project proj) throws Exception {
		return cm.getSpace(proj.getId(), proj.getRootSpaceId());
	}

	// Model
	/**
	 * Returns the list of models in the root space of a Sunglass project
	 * 
	 * @param Project
	 *            a sunglass project
	 * @return HashMap<Integer, Model> hashmap <model id, model>
	 * @throws Exception
	 */
	public HashMap<Integer, Model> getListOfAllModels(Project proj)
			throws Exception {
		HashMap<Integer, Model> modelList = new HashMap<Integer, Model>();
		ArrayList<Model> models = cm.getModels(proj.getId(),
				proj.getRootSpaceId());
		for (Model model : models) {
			modelList.put(model.getId(), model);
		}
		return modelList;
	}

	/**
	 * Download all models from the root space of a Sunglass project
	 * 
	 * @param Project
	 *            a Sunglass project
	 * @param String
	 *            local Sunglass directory path
	 * @return HashMap<Integer, Model> hashmap <model id, model>
	 * @throws Exception
	 */
	public HashMap<Integer, Model> downloadAllModels(Project proj,
			String localDirPath) throws Exception {
		HashMap<Integer, Model> modelList = new HashMap<Integer, Model>();
		ArrayList<Model> models = cm.getModels(proj.getId(),
				proj.getRootSpaceId());
		for (Model model : models) {
			modelList.put(model.getId(), model);
			String localFilePath = localDirPath + "Project " + proj.getId()
					+ "/" + model.getName() + "." + model.getFormat();
			File container = new File(localFilePath);
			model.setLocalFilePath(localFilePath);
			if (!container.exists()) {
				container.getParentFile().mkdirs();
				container.createNewFile();
			}

			String localFilePathMtl = localDirPath + "Project " + proj.getId()
					+ "/" + model.getName() + ".mtl";
			File containerMtl = new File(localFilePathMtl);
			model.setLocalFilePathMtl(localFilePathMtl);
			if (!containerMtl.exists()) {
				containerMtl.getParentFile().mkdirs();
				containerMtl.createNewFile();
			}

			cm.saveToContainer(model.getRemoteFilePath(), container);
			cm.saveToContainer(model.getRemoteFilePathMtl(), containerMtl);
		}

		return modelList;
	}

	/**
	 * Download a model from the root space of a Sunglass project
	 * 
	 * @param Model
	 *            Sunglass model to be downloaded
	 * @param String
	 *            local Sunglass directory path
	 * @return boolean
	 * 			  success
	 * @throws Exception
	 */
	public boolean downloadModel(Model model, String localDirPath) {

		String localFilePath = localDirPath + "Project " + model.getProjectId()
				+ "/" + model.getName() + "." + model.getFormat();
		model.setLocalFilePath(localFilePath);
		try {
			File container = new File(localFilePath);
			if (!container.exists()) {
				container.getParentFile().mkdirs();
				container.createNewFile();
			}

			String localFilePathMtl = localDirPath + model.getProjectId() + "/"
					+ model.getName() + ".mtl";
			model.setLocalFilePathMtl(localFilePathMtl);
			File containerMtl = new File(localFilePathMtl);
			if (!containerMtl.exists()) {
				containerMtl.getParentFile().mkdirs();
				containerMtl.createNewFile();
			}

			cm.saveToContainer(model.getRemoteFilePath(), container);
			cm.saveToContainer(model.getRemoteFilePathMtl(), containerMtl);
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Download a model from the root space of a Sunglass project
	 * 
	 * @param Project
	 *            a Sunglass project
	 * @param int
	 * 			  ID of the model to be downloaded
	 * @param String
	 *            local Sunglass directory path
	 * @return boolean
	 * 			  success
	 * @throws Exception
	 */
	public Model downloadModel(Project proj, int modelId, String localPath)
			throws Exception {
		Model selected = null;
		ArrayList<Model> models = cm.getModels(proj.getId(),
				proj.getRootSpaceId());

		for (Model model : models) {
			if (model.getId() == modelId) {
				selected = model;
				String localFilePath = localPath + model.getProjectId() + "/"
						+ model.getName() + "." + model.getFormat();
				model.setLocalFilePath(localFilePath);
				File container = new File(localFilePath);
				if (!container.exists()) {
					container.getParentFile().mkdirs();
					container.createNewFile();
				}

				String localFilePathMtl = localPath + model.getProjectId()
						+ "/" + model.getName() + ".mtl";
				model.setLocalFilePathMtl(localFilePathMtl);
				File containerMtl = new File(localFilePathMtl);
				if (!containerMtl.exists()) {
					containerMtl.getParentFile().mkdirs();
					containerMtl.createNewFile();
				}

				cm.saveToContainer(model.getRemoteFilePath(), container);
				cm.saveToContainer(model.getRemoteFilePathMtl(), containerMtl);
			}
		}
		if (selected == null) {
			System.out.println("[Sunglass.error] --Model ID:" + modelId
					+ " not found in project.");
			return new Model();
		} else {
			return selected;
		}
	}

	/**
	 * Upload a model to a Sunglass project
	 * 
	 * @param Project
	 *            a Sunglass project
	 *
	 * @return Model
	 * 			  Uploaded Model
	 * 
	 * @throws Exception
	 */
	public Model uploadModelToProject(Project proj) throws Exception {
		// select file
		File selectFile = FileIO.selectFile("Select *.obj file to upload...");
		String filePath = selectFile.getAbsolutePath();
		String filePathMtl = filePath.substring(0, filePath.length() - 3)
				+ "mtl";

		Model m = null;

		String endpoint = "projects/" + proj.getId() + "/spaces/"
				+ proj.getRootSpaceId() + "/models";

		if (new File(filePathMtl).exists()) {
			System.out.println("[Sunglass/upload] --FilepathMtl: "
					+ filePathMtl);
			m = cm.uploadModel(endpoint, "test", new String[] { filePath,
					filePathMtl }, proj.getId());
		} else {
			System.out.println("[Sunglass/upload] --Filepath: " + filePath);
			m = cm.uploadModel(endpoint, "test", new String[] { filePath },
					proj.getId());
		}

		if (m == null)
			System.out
					.println("[Sunglass/uploadModelToProject] -- error uploading");
		return m;
	}

	/**
	 * Return the version of the library.
	 * 
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}
}
