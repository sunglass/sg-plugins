using System;
using System.Collections.Generic;
using System.Text;
using System.Net;
using System.Web;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;
using System.IO;
using System.Security.Cryptography.X509Certificates;
using System.Net.Security;
using System.Xml;


namespace Sunglass
{
    
    ///<Summary>
    /// Gets the answer
    ///</Summary>
    public class ConnectionManager
    {

        private static readonly Encoding encoding = Encoding.UTF8;

        private Boolean dev;

        private String sid;
        private String token;
        private String url;
        private String type;

        ///<Summary>
        /// Constructor
        ///</Summary>

        public ConnectionManager(String url, String sid, String token, String type)
        {
            
            this.url = url;
            this.sid = sid;
            this.token = token;
            this.type = type;
        }

        //Utility HTTP Requests

        //GET 
        private String GetRequest(string path)
        {

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(this.url + path);
            request.Method = "GET";
            request.Accept = type;
            request.ContentType = type;

            string credentials = Convert.ToBase64String(ASCIIEncoding.ASCII.GetBytes(this.sid + ":" + this.token));
            request.Headers.Add("Authorization", "Basic " + credentials);

            HttpWebResponse response = null;
            string responseString = "";

            try
            {
                response = (HttpWebResponse)request.GetResponse();


                if (response.StatusCode == HttpStatusCode.OK)
                {

                    StreamReader reader = new StreamReader(response.GetResponseStream());
                    responseString = reader.ReadToEnd();
                }
            }
            catch (WebException ex)
            {
                if (ex.Status == WebExceptionStatus.ProtocolError) //400 errors
                {

                    Console.WriteLine("HTTP Status Code: " + (int)((HttpWebResponse)ex.Response).StatusCode);
                    Console.WriteLine("Status Description : " + ((HttpWebResponse)ex.Response).StatusDescription);
                    return ((HttpWebResponse)ex.Response).StatusCode.ToString();
                }
                else
                {
                    Console.WriteLine(String.Format("Unhandled status [{0}] returned for url: {1}. {2}", ex.Status, url, ex));
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(String.Format("Could not test url {0}. {1}", url, ex));
            }
            finally
            {
   ;
                //Close Response
                if (response != null) response.Close();
            }

            return responseString;
        }
        //POST
        private String PostRequest(string path, string postData)
        {
            StreamWriter requestWriter;

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(this.url + path);
            request.Method = "POST";
            request.Accept = type;
            request.ContentType = type;

            string credentials = Convert.ToBase64String(ASCIIEncoding.ASCII.GetBytes(this.sid + ":" + this.token));
            request.Headers.Add("Authorization", "Basic " + credentials);

            using (requestWriter = new StreamWriter(request.GetRequestStream()))
            {
                requestWriter.Write(postData);
            }

            HttpWebResponse response = null;
            string responseString = "";

            try
            {
                response = (HttpWebResponse)request.GetResponse();


                if (response.StatusCode == HttpStatusCode.OK)
                {

                    StreamReader reader = new StreamReader(response.GetResponseStream());
                    responseString = reader.ReadToEnd();
                }
            }
            catch (WebException ex)
            {
                if (ex.Status == WebExceptionStatus.ProtocolError) //400 errors
                {

                    Console.WriteLine("HTTP Status Code: " + (int)((HttpWebResponse)ex.Response).StatusCode);
                    Console.WriteLine("Status Description : " + ((HttpWebResponse)ex.Response).StatusDescription);
                    return ((HttpWebResponse)ex.Response).StatusCode.ToString();
                }
                else
                {
                    Console.WriteLine(String.Format("Unhandled status [{0}] returned for url: {1}. {2}", ex.Status, url, ex));
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(String.Format("Could not test url {0}. {1}", url, ex));
            }
            finally
            {
                
                //Close Response
                if (response != null) response.Close();
            }

            return responseString;
        }
        //PUT
        private String PutRequest(string path, string putData)
        {
            StreamWriter requestWriter;

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(this.url + path + "?method=PUT");
            //request.Method = "PUT";
            request.Method = "POST";
            request.Accept = type;
            request.ContentType = type;

            string credentials = Convert.ToBase64String(ASCIIEncoding.ASCII.GetBytes(this.sid + ":" + this.token));
            request.Headers.Add("Authorization", "Basic " + credentials);

            using (requestWriter = new StreamWriter(request.GetRequestStream()))
            {
                requestWriter.Write(putData);
            }

            HttpWebResponse response = null;
            string responseString = "";

            try
            {
                response = (HttpWebResponse)request.GetResponse();


                if (response.StatusCode == HttpStatusCode.OK)
                {

                    StreamReader reader = new StreamReader(response.GetResponseStream());
                    responseString = reader.ReadToEnd();
                }
            }
            catch (WebException ex)
            {
                if (ex.Status == WebExceptionStatus.ProtocolError) //400 errors
                {

                    Console.WriteLine("HTTP Status Code: " + (int)((HttpWebResponse)ex.Response).StatusCode);
                    Console.WriteLine("Status Description : " + ((HttpWebResponse)ex.Response).StatusDescription);
                    return ((HttpWebResponse)ex.Response).StatusCode.ToString();
                }
                else
                {
                    Console.WriteLine(String.Format("Unhandled status [{0}] returned for url: {1}. {2}", ex.Status, url, ex));
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(String.Format("Could not test url {0}. {1}", url, ex));
            }
            finally
            {
                
                
                //Close Response
                if (response != null) response.Close();
            }

            return responseString;
        }
        //DELETE
        private String DeleteRequest(string path)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(this.url + path);
            request.Method = "DELETE";
            request.Accept = type;
            request.ContentType = type;

            string credentials = Convert.ToBase64String(ASCIIEncoding.ASCII.GetBytes(this.sid + ":" + this.token));
            request.Headers.Add("Authorization", "Basic " + credentials);




            HttpWebResponse response = null;
            string responseString = "";

            try
            {
                response = (HttpWebResponse)request.GetResponse();


                if (response.StatusCode == HttpStatusCode.OK)
                {

                    StreamReader reader = new StreamReader(response.GetResponseStream());
                    responseString = reader.ReadToEnd();
                }
            }
            catch (WebException ex)
            {
                if (ex.Status == WebExceptionStatus.ProtocolError) //400 errors
                {

                    Console.WriteLine("HTTP Status Code: " + (int)((HttpWebResponse)ex.Response).StatusCode);
                    Console.WriteLine("Status Description : " + ((HttpWebResponse)ex.Response).StatusDescription);
                    return ((HttpWebResponse)ex.Response).StatusCode.ToString();
                }
                else
                {
                    Console.WriteLine(String.Format("Unhandled status [{0}] returned for url: {1}. {2}", ex.Status, url, ex));
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(String.Format("Could not test url {0}. {1}", url, ex));
            }
            finally
            {
                //Unregister callback
                ServicePointManager.ServerCertificateValidationCallback -= ValidateRemoteCertificate;
                //Close Response
                if (response != null) response.Close();
            }

            return responseString;

        }

        //FILE Upload and Download

        //Multipart Form Upload

        private String PostMultiPartFormDataPostRequest(string path, Dictionary<string, object> postParameters)
        {
            string boundary = "----------------------------" + DateTime.Now.Ticks.ToString("x");
            byte[] formData = GetMultipartFormData(postParameters, boundary);

            HttpWebRequest myRequest = (HttpWebRequest)WebRequest.Create(this.url + path);
            myRequest.Method = "POST";
            myRequest.ContentType = "multipart/form-data; boundary=" + boundary;
            myRequest.KeepAlive = true;
            myRequest.UserAgent = "Sunglass Connection Manager";
            myRequest.ContentLength = formData.Length;
            myRequest.Timeout = System.Threading.Timeout.Infinite;

            string credentials = Convert.ToBase64String(ASCIIEncoding.ASCII.GetBytes(this.sid + ":" + this.token));
            myRequest.Headers.Add("Authorization", "Basic " + credentials);


            Stream rs = myRequest.GetRequestStream();

            rs.Write(formData, 0, formData.Length);
            rs.Close();

            WebResponse response = null;
            try
            {
                response = myRequest.GetResponse();
                Stream stream2 = response.GetResponseStream();
                StreamReader reader2 = new StreamReader(stream2);
                return (reader2.ReadToEnd());

            }
            catch (Exception ex)
            {

                if (response != null)
                {
                    response.Close();
                    response = null;
                }
                return ("Error uploading file" + ex);

            }
            finally
            {

                if (response != null) response.Close();
            }

        }

        [Obsolete("UploadFile is deprecated, please use PostMultiPartFormDataPostRequest instead.")]
        private string UploadFile(string path, string FilePath, string FileName)
        {

            string boundary = "----------------------------" + DateTime.Now.Ticks.ToString("x");

            FileStream fs = new FileStream(FilePath, FileMode.Open, FileAccess.Read);
            byte[] data = new byte[fs.Length];
            fs.Read(data, 0, data.Length);
            fs.Close();


            Dictionary<string, object> postParameters = new Dictionary<string, object>();

            postParameters.Add("file", new FileParameter(data, FileName));

            byte[] formData = GetMultipartFormData(postParameters, boundary);


            HttpWebRequest myRequest = (HttpWebRequest)WebRequest.Create(this.url + path);
            myRequest.Method = "POST";
            myRequest.ContentType = "multipart/form-data; boundary=" + boundary;
            myRequest.KeepAlive = true;
            myRequest.UserAgent = "Sunglass Connection Manager";
            myRequest.ContentLength = formData.Length;
            myRequest.Timeout = System.Threading.Timeout.Infinite;
            string credentials = Convert.ToBase64String(ASCIIEncoding.ASCII.GetBytes(this.sid + ":" + this.token));
            myRequest.Headers.Add("Authorization", "Basic " + credentials);

            Stream rs = myRequest.GetRequestStream();

            rs.Write(formData, 0, formData.Length);
            rs.Close();

            WebResponse wresp = null;
            try
            {
                wresp = myRequest.GetResponse();
                Stream stream2 = wresp.GetResponseStream();
                StreamReader reader2 = new StreamReader(stream2);
                return (string.Format("File uploaded, server response is: {0}", reader2.ReadToEnd()));

            }
            catch (Exception ex)
            {

                if (wresp != null)
                {
                    wresp.Close();
                    wresp = null;
                }
                return ("Error uploading file" + ex);

            }
            finally
            {
                myRequest = null;
            }

        }

        private static byte[] GetMultipartFormData(Dictionary<string, object> postParameters, string boundary)
        {
            Stream formDataStream = new System.IO.MemoryStream();
            bool needsCLRF = false;

            foreach (var param in postParameters)
            {
                // Thanks to feedback from commenters, add a CRLF to allow multiple parameters to be added.
                // Skip it on the first parameter, add it to subsequent parameters.
                if (needsCLRF)
                    formDataStream.Write(encoding.GetBytes("\r\n"), 0, encoding.GetByteCount("\r\n"));

                needsCLRF = true;

                if (param.Value is FileParameter)
                {
                    FileParameter fileToUpload = (FileParameter)param.Value;

                    // Add just the first part of this param, since we will write the file data directly to the Stream
                    string header = string.Format("--{0}\r\nContent-Disposition: form-data; name=\"{1}\"; filename=\"{2}\";\r\nContent-Type: {3}\r\n\r\n",
                        boundary,
                        param.Key,
                        fileToUpload.FileName ?? param.Key,
                        fileToUpload.ContentType ?? "application/octet-stream");

                    formDataStream.Write(encoding.GetBytes(header), 0, encoding.GetByteCount(header));

                    // Write the file data directly to the Stream, rather than serializing it to a string.
                    formDataStream.Write(fileToUpload.File, 0, fileToUpload.File.Length);
                }
                else
                {
                    string postData = string.Format("--{0}\r\nContent-Disposition: form-data; name=\"{1}\"\r\n\r\n{2}",
                        boundary,
                        param.Key,
                        param.Value);
                    formDataStream.Write(encoding.GetBytes(postData), 0, encoding.GetByteCount(postData));
                }
            }

            // Add the end of the request.  Start with a newline
            string footer = "\r\n--" + boundary + "--\r\n";
            formDataStream.Write(encoding.GetBytes(footer), 0, encoding.GetByteCount(footer));

            // Dump the Stream into a byte[]
            formDataStream.Position = 0;
            byte[] formData = new byte[formDataStream.Length];
            formDataStream.Read(formData, 0, formData.Length);
            formDataStream.Close();

            return formData;
        }

        //Simple File Downloader
        private void DownloadFile(string path, string filename, string url)
        {
            string filepath = path + filename;

            try
            {
                WebClient client = new WebClient();
          
                string credentials = Convert.ToBase64String(ASCIIEncoding.ASCII.GetBytes(this.sid + ":" + this.token));
                client.Headers.Add("Authorization", "Basic " + credentials);

                client.DownloadFile(url, filepath);
            }
            catch (Exception ex)
            {

                Console.WriteLine(ex.GetType().FullName);
                Console.WriteLine(ex.GetBaseException().ToString());
            }
        }


        ///<Summary>
        ///Client Authentication
        ///</Summary>
        public Boolean Authenticate()
        {


            Boolean authenticated = false;
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(this.url + "projects");
            request.Method = "GET";
            request.Accept = this.type;
            request.ContentType = this.type;

            string credentials = Convert.ToBase64String(ASCIIEncoding.ASCII.GetBytes(this.sid + ":" + this.token));
            request.Headers.Add("Authorization", "Basic " + credentials);



            HttpWebResponse response = null;

            try
            {
                response = (HttpWebResponse)request.GetResponse();


                if (response.StatusCode == HttpStatusCode.OK)
                {
                    authenticated = true;

                }
            }
            catch (WebException ex)
            {
                if (ex.Status == WebExceptionStatus.ProtocolError) //400 errors
                {
                    Console.WriteLine("HTTP Status Code: " + (int)((HttpWebResponse)ex.Response).StatusCode);
                    Console.WriteLine("Status Description : " + ((HttpWebResponse)ex.Response).StatusDescription);
                    return false;
                }
                else
                {
                    Console.WriteLine(String.Format("Unhandled status [{0}] returned for url: {1}. {2}", ex.Status, url, ex));
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(String.Format("Could not test url {0}. {1}", url, ex));
            }
            finally
            {

                //Close Response
                if (response != null) response.Close();
            }

            return authenticated;


        }

        /*
        
         * GET
                
        */

        ///<Summary>
        /// Get User Details
        ///</Summary>
        public User GetUserDetails()
        {
            string userJson = this.GetRequest("users");
            User user = JsonConvert.DeserializeObject<User>(userJson);
            return user;
        }

        ///<Summary>
        /// Get Projects
        ///</Summary>
        public ProjectList GetProjects()
        {
            string projectJson = this.GetRequest("projects");
            ProjectList projects = JsonConvert.DeserializeObject<ProjectList>(projectJson);
            return projects;
        }

        ///<Summary>
        /// Get Project By ID
        ///</Summary>
        public Project GetProject(string id)
        {
            string projectJson = this.GetRequest("projects/" + id);
            Project project = JsonConvert.DeserializeObject<Project>(projectJson);
            return project;
        }

        ///<Summary>
        /// Get All Spaces
        ///</Summary>
        public SpaceList GetSpaces(Project project)
        {
            string spaceJson = this.GetRequest("projects/" + project.id + "/spaces");
            SpaceList spaces = JsonConvert.DeserializeObject<SpaceList>(spaceJson);
            return spaces;
        }

        ///<Summary>
        ///Get Root Space
        ///</Summary>
        public Space GetRootSpace(Project project)
        {
            SpaceList spaceList = this.GetSpaces(project);
            foreach (Space space in spaceList.spaces)
            {
                if (this.IsRootSpace(space))
                {
                    return space;
                }
            }
            return spaceList.spaces[0];
        }

        ///<Summary>
        ///Get Space By ID
        ///</Summary>
        public Space GetSpace(Project project, string id)
        {
            string spaceJson = this.GetRequest("projects/" + project.id + "/spaces/" + id);
            Space space = JsonConvert.DeserializeObject<Space>(spaceJson);
            return space;
        }

        ///<Summary>
        ///Get SubSpaces By Parent ID
        ///</Summary>
        public SubSpaceList GetSubSpaces(Project project, Space parent)
        {
            string spaceJson = this.GetRequest("projects/" + project.id + "/spaces/" + parent.id + "/subspaces");
            SubSpaceList descendants = JsonConvert.DeserializeObject<SubSpaceList>(spaceJson);
            return descendants;

        }

        ///<Summary>
        ///Get SubSpace
        ///</Summary>
        public Space GetSubSpace(Project project, Space parent, string id)
        {
            string spaceJson = this.GetRequest("projects/" + project.id + "/spaces/" + parent.id + "/subspaces/" + id);
            Space space = JsonConvert.DeserializeObject<Space>(spaceJson);
            return space;

        }

        ///<Summary>
        ///Get Space Versions
        ///</Summary>
        public VersionList GetSpaceVersions(Project project, string id)
        {
            string spaceVersionJson = this.GetRequest("projects/" + project.id + "/spaces/" + id + "/versions");
            VersionList versions = JsonConvert.DeserializeObject<VersionList>(spaceVersionJson);
            return versions;
        }

        ///<Summary>
        ///Test if space is root of project
        ///</Summary>
        public Boolean IsRootSpace(Space space)
        {
            if (space.id == space.parentSpaceId)
            {
                return true;
            }
            return false;
        }

        ///<Summary>
        ///Get Details of Models in Project
        ///</Summary>
        public ModelList GetModels(Project project)
        {
            string modelJson = this.GetRequest("projects/" + project.id + "/models");
            ModelList models = JsonConvert.DeserializeObject<ModelList>(modelJson);
            return models;
        }

        ///<Summary>
        ///Get Model Details
        ///</Summary>
        public Model GetModelDetails(Project project, string id)
        {
            string modelJson = this.GetRequest("projects/" + project.id + "/models/" + id);
            Model model = JsonConvert.DeserializeObject<Model>(modelJson);
            return model;
        }

        ///<Summary>
        ///Get Model Details By Type : Right now, only "original", "prc", "json", "bin" are supported
        ///</Summary>
        public Model GetModelDetails(Project project, string id, string type)
        {
            string modelJson = this.GetRequest("projects/" + project.id + "/models/" + id + "/data?format=" + type);
            Model model = JsonConvert.DeserializeObject<Model>(modelJson);
            return model;
        }

        ///<Summary>
        ///Get Models References in Space
        ///</Summary>
        public MetaModelList GetModelReferencesDetails(Project project, Space space)
        {
            string modelReferenceJson = this.GetRequest("projects/" + project.id + "/spaces/" + space.id + "/metamodels");
            MetaModelList modelReferences = JsonConvert.DeserializeObject<MetaModelList>(modelReferenceJson);
            return modelReferences;
        }

        ///<Summary>
        ///Get Model Reference Details
        ///</Summary>
        public MetaModel GetModelReferenceDetails(Project project, Space space, string id)
        {
            string modelReferenceJson = this.GetRequest("projects/" + project.id + "/spaces/" + space.id + "/metamodels/" + id);
            MetaModel modelReference = JsonConvert.DeserializeObject<MetaModel>(modelReferenceJson);
            return modelReference;
        }

        ///<Summary>
        ///Get Model Versions
        ///</Summary>
        public VersionList GetModelVersions(Project project, string id)
        {
            string versionListJson = this.GetRequest("projects/" + project.id + "/models/" + id + "/versions");
            VersionList versions = JsonConvert.DeserializeObject<VersionList>(versionListJson);
            return versions;
        }

        public Version getModelVersion(Project project, Model model, string id)
        {
            string versionJson = this.GetRequest("projects/" + project.id + "/models/" + model.id + "/versions/"+id);
            Version version = JsonConvert.DeserializeObject<Version>(versionJson);
            return version;
        }

        ///<Summary>
        ///Download Model From Project, returns the full url for the file
        ///</Summary>
        public string DownloadModel(Project project, string id, string path)
        {
            Model model = this.GetModelDetails(project, id);
            string url = this.url + "projects/" + project.id + "/models/" + model.id + "/data?format=original";
            string fileName = model.name;
            this.DownloadFile(path, fileName, url);
            return url;
        }

        ///<Summary>
        ///Download Model From Project, returns the full url for the file
        ///</Summary>
        public string DownloadModel(Project project, string id, string fileName, string path)
        {
            Model model = this.GetModelDetails(project, id);
            string url = this.url + "projects/" + project.id + "/models/" + model.id + "/data?format=original";
            this.DownloadFile(path, fileName, url);
            return url;
        }

        ///<Summary>
        ///Download Model From Project, returns the full url for the file
        ///</Summary>
        public string DownloadModel(Project project, string id, string fileName, string path, string modelType)
        {
            Model model = this.GetModelDetails(project, id);
            string url = this.url + "projects/" + project.id + "/models/" + model.id + "/data?format=" + modelType;
            this.DownloadFile(path, fileName, url);
            return url;
        }

        ///<Summary>
        ///Download Model From Space
        ///</Summary>
        public string DownloadModel(Project project, Space space, string id, string path, string fileName)
        {
            return null;
        }

        /*
        
         * CREATE
        
        */


        ///<Summary>
        ///Create Project with Name and Description
        ///</Summary>
        public Project CreateProject(string name, string description)
        {
            string projectData = "{\"name\": \"" + name + "\",\"description\": \"" + description + "\"}";
            string projectJson = this.PostRequest("projects", projectData);
            Project project = JsonConvert.DeserializeObject<Project>(projectJson);
            return project;
        }

        ///<Summary>
        ///Create Project with Name
        ///</Summary>
        public Project CreateProject(string name)
        {
            string projectData = "{\"name\": \"" + name + "\"}";
            string projectJson = this.PostRequest("projects", projectData);
            Project project = JsonConvert.DeserializeObject<Project>(projectJson);
            return project;
        }

        ///<Summary>
        ///Create Space
        ///</Summary>
        public Space CreateSpace(Project project, Space parentSpace, string name)
        {
            string spaceData = "{\"name\": \"" + name + "\"}";
            string spaceJson = this.PostRequest("projects/" + project.id + "/spaces/" + parentSpace.id + "/subspaces", spaceData);
            Space space = JsonConvert.DeserializeObject<Space>(spaceJson);
            return space;
        }

        ///<Summary>
        ///Create a Model Reference (Metamodel) to a Model
        ///</Summary>
        public MetaModel CreateModelReference(Project project, Space space, Model model)
        {
            string modelData = "{\"modelId\": \"" + model.id + "\"}";
            string modelJson = this.PostRequest("projects/" + project.id + "/spaces/" + space.id + "/metamodels", modelData);
            MetaModel modelReference = JsonConvert.DeserializeObject<MetaModel>(modelJson);
            return modelReference;
        }

        ///<Summary>
        ///Upload Model To Project
        ///</Summary>
        [Obsolete("UploadModel is deprecated, please use CreateModelAndVersion or CreateModelAndVersionAndReference instead.")]
        public string UploadModel(Project project, string filePath, string fileName)
        {
            string response = this.UploadFile("projects/" + project.id + "/models", filePath, fileName);
            return response;
        }

        ///<Summary>
        ///Multipart file params for the model files. If an image file with name cover_img.(jpg/png/...) is included then this image will be used as the cover image for this version
        ///</Summary>
        [Obsolete("UploadModel is deprecated, please use CreateModelAndVersion or CreateModelAndVersionAndReference instead.")]
        public string UploadModel(Project project, Space space, string filePath, string fileName)
        {
            string response = this.UploadFile("projects/" + project.id + "/models?spaceId=" + space.id, filePath, fileName);
            return response;
        }

        public ModelList CreateModelAndVersion(Project project, string message, string modelFileName, string modelPath, string coverImageName, string coverImagePath)
        {
     
            // Read model file data
            FileStream modelFs = new FileStream(modelPath, FileMode.Open, FileAccess.Read);
            byte[] modelData = new byte[modelFs.Length];
            modelFs.Read(modelData, 0, modelData.Length);
            modelFs.Close();

            // Read coverImage data
            FileStream imageFs = new FileStream(coverImagePath, FileMode.Open, FileAccess.Read);
            byte[] imageData = new byte[imageFs.Length];
            imageFs.Read(imageData, 0, imageData.Length);
            imageFs.Close();


            // Generate post objects
            Dictionary<string, object> postParameters = new Dictionary<string, object>();
            postParameters.Add("message", message);
            postParameters.Add("file", new FileParameter(modelData,modelFileName));
            postParameters.Add("cover_img", new FileParameter(imageData, coverImageName));

            //make the request
            String modelJson = this.PostMultiPartFormDataPostRequest("projects/" + project.id + "/models", postParameters);           
           
            ModelList modellist = JsonConvert.DeserializeObject<ModelList>(modelJson);
            return modellist;
        }


        public Model CreateModelAndVersionAndReference(Project project, Space space, string message, string modelFileName, string modelPath, string coverImageName, string coverImagePath)
        {

            // Read model file data
            FileStream modelFs = new FileStream(modelPath, FileMode.Open, FileAccess.Read);
            byte[] modelData = new byte[modelFs.Length];
            modelFs.Read(modelData, 0, modelData.Length);
            modelFs.Close();

            // Read coverImage data
            FileStream imageFs = new FileStream(coverImagePath, FileMode.Open, FileAccess.Read);
            byte[] imageData = new byte[imageFs.Length];
            imageFs.Read(imageData, 0, imageData.Length);
            imageFs.Close();


            // Generate post objects
            Dictionary<string, object> postParameters = new Dictionary<string, object>();
            postParameters.Add("message", message);
            postParameters.Add("file", new FileParameter(modelData, modelFileName));
            postParameters.Add("cover_img", new FileParameter(imageData, coverImageName));

            //make the request
            String modelJson = this.PostMultiPartFormDataPostRequest("projects/" + project.id + "/models?spaceId=" + space.id, postParameters);

            Model model = JsonConvert.DeserializeObject<Model>(modelJson);
            return model;

        }


        /*
        
         * Set 
                
        */


        ///<Summary>
        ///TODO Set Project Name, Description
        ///</Summary>
        public Project SetProjectName(Project project, string name)
        {

            string projectData = "{\"name\": \"" + name + "\"}";
            string projectJson = this.PutRequest("projects/" + project.id, projectData);
            Project setProject = JsonConvert.DeserializeObject<Project>(projectJson);
            return setProject;
        }
        ///<Summary>
        ///
        ///</Summary>
        public Project SetProjectDescription(Project project, string description)
        {
            string projectData = "{\"description\": \"" + description + "\"}";
            string projectJson = this.PutRequest("projects/" + project.id, projectData);
            Project setProject = JsonConvert.DeserializeObject<Project>(projectJson);
            return setProject;
        }

        ///<Summary>
        ///
        ///</Summary>
        public Project SetProject(Project project)
        {
            string projectData = JsonConvert.SerializeObject(project);
            string projectJson = this.PutRequest("projects/" + project.id, projectData);
            Project setProject = JsonConvert.DeserializeObject<Project>(projectJson);
            return setProject;
        }

       

        ///<Summary>
        ///
        ///</Summary>
        [Obsolete("SetModel is deprecated, please use SetModelAndVersion instead.")]
        public string SetModel(Project project, Model model, string filePath, string fileName)
        {
            string response = this.UploadFile("projects/" + project.id + "/models/" + model.id, filePath, fileName);
            return response;
        }

        public Model SetModelAndVersion(Project project, Model model, string message, string modelFileName, string modelPath, string coverImageName, string coverImagePath)
        {
            // Read model file data
            FileStream modelFs = new FileStream(modelPath, FileMode.Open, FileAccess.Read);
            byte[] modelData = new byte[modelFs.Length];
            modelFs.Read(modelData, 0, modelData.Length);
            modelFs.Close();

            // Read coverImage data
            FileStream imageFs = new FileStream(coverImagePath, FileMode.Open, FileAccess.Read);
            byte[] imageData = new byte[imageFs.Length];
            imageFs.Read(imageData, 0, imageData.Length);
            imageFs.Close();


            // Generate post objects
            Dictionary<string, object> postParameters = new Dictionary<string, object>();
            postParameters.Add("message", message);
            postParameters.Add("file", new FileParameter(modelData, modelFileName));
            postParameters.Add("cover_img", new FileParameter(imageData, coverImageName));

            //make the request
            String modelJson = this.PostMultiPartFormDataPostRequest("projects/" + project.id + "/models/"+model.id, postParameters);

            NewModel returnModel = JsonConvert.DeserializeObject<NewModel>(modelJson);
            return returnModel.model;
        }

        ///<Summary>
        ///TODO Set Space - Name, Transformation Matrix
        ///</Summary>
        public Space SetSpaceName(Project project, Space space, string name)
        {
            string spaceData = "{\"name\": \"" + name + "\"}";
            string spaceJson = this.PutRequest("projects/" + project.id + "/spaces/" + space.id, spaceData);
            Space setSpace = JsonConvert.DeserializeObject<Space>(spaceJson);
            return setSpace;
        }

        ///<Summary>
        ///
        ///</Summary>
        public Space SetSpace(Project project, Space space)
        {
            string spaceData = JsonConvert.SerializeObject(space);
            string spaceJson = this.PutRequest("projects/" + project.id + "/spaces/" + space.id, spaceData);
            Space setSpace = JsonConvert.DeserializeObject<Space>(spaceJson);
            return setSpace;

        }

        ///<Summary>
        ///
        ///</Summary>
        public MetaModel SetModelReference(Project project, Space space, MetaModel modelReference)
        {
            string modelReferenceData = JsonConvert.SerializeObject(modelReference);
            string modelReferenceJson = this.PostRequest("projects/" + project.id + "/spaces/" + space.id + "/metamodels/" + modelReference.id, modelReferenceData);
            MetaModel setModelReference = JsonConvert.DeserializeObject<MetaModel>(modelReferenceJson);
            return setModelReference;
        }

        

        /*
        
         * Delete 
                
        */

        ///<Summary>
        ///Delete Project
        ///</Summary>
        public void DeleteProject(string id)
        {
            this.DeleteRequest("projects/" + id);
        }

        ///<Summary>
        ///Delete Space
        ///</Summary>
        public void DeleteSpace(Project project, string id)
        {
            this.DeleteRequest("projects/" + project.id + "/spaces/" + id);
        }

        ///<Summary>
        ///Delete Model
        ///</Summary>
        public void DeleteModel(Project project, string id)
        {
            this.DeleteRequest("projects/" + project.id + "/models/" + id);
        }

        ///<Summary>
        ///Delete Model Reference
        ///</Summary>
        public void DeleteModelReference(Project project, Space space, string id)
        {
            this.DeleteRequest("projects/" + project.id + "/spaces/" + space.id + "/metamodels/" + id);
        }

    }


    /*
        
     * Classses for Sunglass API Objects
            
    */

    public class Account
    {
        public int id { get; set; }
        public string name { get; set; }
        public string emails { get; set; }
        public string href { get; set; }
        public AccountLinks links { get; set; }
        public string createdAt { get; set; }
        public string modifiedAt { get; set; }
        public string sid { get; set; }
        public string token { get; set; }
        public AccountUsage usage { get; set; }
    }

    public class AccountLinks
    {
        public string projects { get; set; }
    }

    public class AccountUsage
    {
        public int latestProject { get; set; }
        public string latestSpace { get; set; }
        public string latestSpaceRole { get; set; }
    }

    ///<Summary>
    ///A SG User Object
    ///</Summary>
    public class User
    {
        ///<Summary>
        ///Id of User
        ///</Summary>
        public int id { get; set; }
        ///<Summary>
        ///Name of User
        ///</Summary>
        public string emails { get; set; }
        ///<Summary>
        ///Email of User. Important because Sunglass works on uniqueness of Emails
        ///</Summary>
        public string href { get; set; }
        ///<Summary>
        ///See UserLinks Object
        ///</Summary>
        public UserLinks links { get; set; }
        ///<Summary>
        ///Creation date
        ///</Summary>
        public string createdAt { get; set; }
        ///<Summary>
        ///last modified Date
        ///</Summary>
        public string modifiedAt { get; set; }
        ///<Summary>
        ///See Usage Object
        ///</Summary>
        public Usage usage { get; set; }
    }
    ///<Summary>
    /// Recent Usage Details of User
    ///</Summary>
    public class Usage
    {
        ///<Summary>
        /// The last project viewed by User
        ///</Summary>
        public int latestProject { get; set; }
        ///<Summary>
        /// The id of the latest Stage (Space?) Viewed by User
        ///</Summary>
        public string latestStage { get; set; }
        ///<Summary>
        /// The role of the User in that Stage (Space?)
        ///</Summary>
        public string latestStageRole { get; set; }
    }

    ///<Summary>
    ///Project Object
    ///</Summary>
    public class Project
    {
        ///<Summary>
        ///Id of Project
        ///</Summary>
        public int id { get; set; }
        ///<Summary>
        ///Name of the Project
        ///</Summary>
        public string name { get; set; }
        ///<Summary>
        ///Description of Project, if provided on creation
        ///</Summary>
        public string description { get; set; }
        ///<Summary>
        ///Public / Private project
        ///</Summary>
        public string visibility { get; set; }
        ///<Summary>
        ///Api endpoit for this project
        ///</Summary>
        public string href { get; set; }
        ///<Summary>
        ///See ProjectLinks Object
        ///</Summary>
        public ProjectLinks links { get; set; }
        ///<Summary>
        ///Creation date of the project
        ///</Summary>
        public string createdAt { get; set; }
        ///<Summary>
        ///last modified Date of the Project
        ///</Summary>
        public string modifiedAt { get; set; }
    }

    ///<Summary>
    ///Project List
    ///</Summary>
    public class ProjectList
    {
        ///<Summary>
        ///List of User Projects with owner priveliges
        ///</Summary>
        public List<Project> owner { get; set; }
        ///<Summary>
        ///List of User Projects with admin priveliges
        ///</Summary>
        public List<Project> admin { get; set; }
        ///<Summary>
        ///List of User Projects with editor priveliges
        ///</Summary>
        public List<Project> editor { get; set; }
        ///<Summary>
        ///List of User Projects with guest priveliges
        ///</Summary>
        public List<Project> guest { get; set; }
    }

    ///<Summary>
    ///Space Object
    ///</Summary>
    public class Space
    {
        ///<Summary>
        ///Id of Space
        ///</Summary>
        public string id { get; set; }
        ///<Summary>
        ///name of Space
        ///</Summary>
        public string name { get; set; }
        ///<Summary>
        ///User's role in this project
        ///</Summary>
        public string role { get; set; }
        ///<Summary>
        ///Api Endpoint for this space
        ///</Summary>
        public string href { get; set; }
        ///<Summary>
        ///Id of Space to which this space belongs (if this equals this space's id then this is the root space)
        ///</Summary>
        public string parentSpaceId { get; set; }
        ///<Summary>
        ///Id of Project to which this space belongs
        ///</Summary>
        public int projectId { get; set; }
        ///<Summary>
        ///Transformation Matrix in parent space
        ///</Summary>
        public List<double> transformMatrix { get; set; }
        ///<Summary>
        ///List of all groups
        ///</Summary>
        public string groupsList { get; set; }
        ///<Summary>
        ///Creation date
        ///</Summary>
        public string createdAt { get; set; }
        ///<Summary>
        ///last modified Date
        ///</Summary>
        public string modifiedAt { get; set; }
        ///<Summary>
        ///See SpaceLinks Object
        ///</Summary>
        public SpaceLinks links { get; set; }
    }

    ///<Summary>
    ///Space List
    ///</Summary>
    public class SpaceList
    {
        ///<Summary>
        ///Space List
        ///</Summary>
        public List<Space> spaces { get; set; }
    }

    ///<Summary>
    ///Space List
    ///</Summary>
    public class SubSpaceList
    {
        ///<Summary>
        ///Space List
        ///</Summary>
        public List<Space> descendants { get; set; }
    }

    public class Metadata
    {
    }

    ///<Summary>
    ///Model Object
    ///</Summary>
    public class Model
    {
        ///<Summary>
        ///Id of Model
        ///</Summary>
        public string id { get; set; }
        ///<Summary>
        ///Name of Model
        ///</Summary>
        public string name { get; set; }
        ///<Summary>
        ///can be Textured if the model has textures
        ///</Summary>
        public string modelType { get; set; }
        ///<Summary>
        ///Model Object
        ///</Summary>
        public int projectId { get; set; }
        ///<Summary>
        ///Description of Model, if added
        ///</Summary>
        public string description { get; set; }
        ///<Summary>
        ///Model Metadata
        ///</Summary>
        public Metadata metaData { get; set; }
        ///<Summary>
        ///Id of User who created this model
        ///</Summary>
        public int creatorId { get; set; }
        ///<Summary>
        ///Model Object
        ///</Summary>
        public string createdAt { get; set; }
        ///<Summary>
        ///Creation date
        ///</Summary>
        public string modifiedAt { get; set; }
        ///<Summary>
        ///last modified Date
        ///</Summary>
        public string href { get; set; }

       public string version { get; set; }
    }

    public class NewModel
    {
        ///<Summary>
        ///Model List
        ///</Summary>
        public Model model { get; set; }
    }
    ///<Summary>
    ///Model List
    ///</Summary>
    public class ModelList
    {
        ///<Summary>
        ///Model List
        ///</Summary>
        public List<Model> models { get; set; }
    }

    ///<Summary>
    ///Model Reference Object (in the api these are called MetaModels but that name is subject to change)
    ///</Summary>
    public class MetaModel
    {
        ///<Summary>
        ///Id of Model Reference
        ///</Summary>
        public int id { get; set; }
        ///<Summary>
        /// The id the space that this Model Reference belongs to
        ///</Summary>
        public string spaceId { get; set; }
        ///<Summary>
        ///The id of the Project that this Model Reference belongs to
        ///</Summary>
        public int projectId { get; set; }
        ///<Summary>
        ///Id of Model that defines the geometry for this model reference
        ///</Summary>
        public string modelId { get; set; }
        ///<Summary>
        ///Transformation Matrix used to position model in space
        ///</Summary>
        public List<double> transformMatrix { get; set; }
        ///<Summary>
        ///Model Reference Metadata
        ///</Summary>
        public Metadata metaData { get; set; }
        ///<Summary>
        ///Creation date
        ///</Summary>
        public string createdAt { get; set; }
        ///<Summary>
        ///last modified Date
        ///</Summary>
        public string modifiedAt { get; set; }
        ///<Summary>
        ///See ModelDetails Object
        ///</Summary>
        public ModelDetails modelDetails { get; set; }
        ///<Summary>
        ///Endpoint for this model in the API
        ///</Summary>
        public string href { get; set; }
    }

    ///<Summary>
    ///Model Reference List
    ///</Summary>
    public class MetaModelList
    {
        ///<Summary>
        ///Model Reference List
        ///</Summary>
        public List<MetaModel> MetaModels { get; set; }
    }

    ///<Summary>
    ///Details for a Model Defintion from a Model Reference List
    ///</Summary>
    public class ModelDetails
    {
        ///<Summary>
        ///Id of Model
        ///</Summary>   
        public string id { get; set; }
        ///<Summary>
        ///Name of Model
        ///</Summary>
        public string name { get; set; }
        ///<Summary>
        ///can be Textured if the model has textures
        ///</Summary>
        public string modelType { get; set; }
        ///<Summary>
        ///The id of the Project that this Model belongs to
        ///</Summary>
        public int projectId { get; set; }
        ///<Summary>
        ///Description of Model, if added
        ///</Summary>
        public string description { get; set; }
        ///<Summary>
        ///Model metadata
        ///</Summary>
        public Metadata metaData { get; set; }
        ///<Summary>
        ///Id of User who created this model
        ///</Summary>
        public int creatorId { get; set; }
        ///<Summary>
        ///Creation date
        ///</Summary>
        public string createdAt { get; set; }
        ///<Summary>
        ///last modified Date
        ///</Summary>
        public string modifiedAt { get; set; }
        ///<Summary>
        ///Api endpoint for this model
        ///</Summary>
        public string href { get; set; }
    }

    ///<Summary>
    ///Version Object
    ///</Summary>
    public class Version
    {
        ///<Summary>
        ///Id of this version
        ///</Summary>
        public string id { get; set; }
        ///<Summary>
        ///A descriptive message about this version.
        ///</Summary>
        public string message { get; set; }
        ///<Summary>
        ///List of IDs of versions from which this version is derived
        ///</Summary>
        public List<string> parents { get; set; }
        ///<Summary>
        ///A timestamp for the version
        ///</Summary>
        public string timestamp { get; set; }
        ///<Summary>
        ///created Date
        ///</Summary>
        public string createdAt { get; set; }
        ///<Summary>
        ///List of users involved in this version
        ///</Summary>
        public List<string> authors { get; set; }

        ///<Summary>
        ///created Date
        ///</Summary>
        public string origChecksum { get; set; }

        ///<Summary>
        ///created Date
        ///</Summary>
        public string coverImage { get; set; }
    }

    ///<Summary>
    ///Version List
    ///</Summary>
    public class VersionList
    {
        ///<Summary>
        ///Version list
        ///</Summary>
        public List<Version> versions { get; set; }
    }

    ///<Summary>
    ///User Links
    ///</Summary>
    public class UserLinks
    {
        ///<Summary>
        ///Api endpoints for this user's projects
        ///</Summary>
        public string projects { get; set; }
    }

    ///<Summary>
    ///Space Links
    ///</Summary>
    public class SpaceLinks
    {
        ///<Summary>
        ///Api endpoint for the project that this space belongs to
        ///</Summary>
        public string project { get; set; }
        ///<Summary>
        ///Api endpoint for the space that this space belongs to
        ///</Summary>
        public string parentSpace { get; set; }
        ///<Summary>
        ///Api endpoint for the subspaces of this space
        ///</Summary>
        public string subSpaces { get; set; }
        ///<Summary>
        ///Api endpoint for Model References in this space
        ///</Summary>
        public string metaModels { get; set; }
        ///<Summary>
        ///Api endpoint for views in this space
        ///</Summary>
        public string views { get; set; }
    }

    ///<Summary>
    ///Project Links
    ///</Summary>
    public class ProjectLinks
    {
        ///<Summary>
        ///Api endpoint for this project's spaces
        ///</Summary>
        public string spaces { get; set; }
        ///<Summary>
        ///Api endpoint for the rootSpace of this project
        ///</Summary>
        public string rootSpace { get; set; }
    }

    ///<Summary>
    ///File Parameters for Multipart upload
    ///</Summary>
    public class FileParameter
    {
        ///<Summary>
        ///File Data
        ///</Summary>
        public byte[] File { get; set; }
        ///<Summary>
        ///Filename
        ///</Summary>
        public string FileName { get; set; }
        ///<Summary>
        ///File Content type
        ///</Summary>
        public string ContentType { get; set; }

        ///<Summary>
        ///File Parameter
        ///</Summary>
        public FileParameter(byte[] file) : this(file, null) { }
        ///<Summary>
        ///File Parameter
        ///</Summary>
        public FileParameter(byte[] file, string filename) : this(file, filename, null) { }
        ///<Summary>
        ///File Parameter
        ///</Summary>
        public FileParameter(byte[] file, string filename, string contenttype)
        {
            File = file;
            FileName = filename;
            ContentType = contenttype;
        }
    }

}