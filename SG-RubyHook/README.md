Ruby Client for Sunglass.io
===================================

A basic wrapper for the Sunglass.io API
The goal is to quickly give people access to the api
through a couple methods that wrap http requests.

for more information see
http://www.sunglass.io/api

Dependencies
------------
+	rest-client - http://rubygems.org/gems/rest-client
+	json - http://flori.github.com/json/

These can both easily be installed using ruby gems with these commands

	>gem install rest-client
	>get install json
	
Methods
-------

	get_collection(path)
		
>returns a ruby hash containing response data from the api endpoint
			
>path : the api endpoint
	
	upload_file(path, file)
		
>uploads a model to sunglass (can be .obj, .stl, .dae, .3ds)
		
>path : the api endpoint

>file : the local path of the file to be uploaded