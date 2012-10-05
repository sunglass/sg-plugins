Python Client for Sunglass.io
===================================

A basic wrapper for the Sunglass.io API
The goal is to quickly give people access to the api
through a couple methods that wrap http requests.

for more information see
http://www.sunglass.io/api

Dependencies
------------
+	poster - http://atlee.ca/software/poster/

This can be installed using easy install with this command

	>sudo easy_install poster

Methods
-------

	get_collection(path)
		
>returns a dict containing response data from the api endpoint
		
>path : the api endpoint
	
	upload_file(path, name, file)
		
>uploads a model to sunglass (can be .obj, .stl, .dae, .3ds)
		
>path : the api endpoint

>name : the name to upload the file as

>file : the local path of the file to be uploaded