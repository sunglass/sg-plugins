require 'rubygems'
#gem install rest-client
require 'rest_client'
#gem install json
require 'json'


@base_url = "https://sunglass.io"
@sid    = "" #enter your sid 
@token  = "" #enter your secret token
@filepath = "" #the path of the file you want to upload
@resource = RestClient::Resource.new( @base_url , :user => @sid, :password => @token )

def get_collection(path)
  response = @resource[path].get(:headers => { :accept => :json, :content_type => :json })
  puts response.code
  JSON.parse(response)
end

#get projects
projects = get_collection("/api/v1/projects")


def upload_model(path, file)

  response = resource[path + "/models"].post( :myfile => File.new(file, 'r')) # note use 'rb' instead of 'r' if uploading a binary file in windows
  puts response
  JSON.parse(response)
end

#upload model
puts upload_model(projects["projects"][1]["links"]["rootSpace"], @filepath)
