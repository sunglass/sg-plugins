#Sunglass.io Python Hook
#tested with Python 2.7.1

#sudo easy_install poster
from poster.encode import multipart_encode
from poster.streaminghttp import register_openers
from urllib2 import Request, urlopen, URLError

import urllib2
import json
import base64

base_url = "https://sunglass.io"
sid = ""  
token = ""
filepath = ""
base64 = base64.encodestring('%s:%s' % (sid, token)).replace('\n', '')

register_openers()

def get_collection(path):
    request = urllib2.Request(base_url + path)
    request.add_header("Authorization", "Basic %s" % base64)
    request.add_header("Content-Type","application/json")
    result = urllib2.urlopen(request)
    response = result.read();
    result.close()
    return json.loads(response)

projects = get_collection("/api/v1/projects")

 
def upload_file(path, name ,file):
    print "lets check: " + base_url + path + "/models"
    
    datagen, headers = multipart_encode({name: open(file, "r")}) #NOTE CHANGE "r" to "rb" when reading binary files
    request = urllib2.Request(base_url + path + "/models", datagen, headers)
    
    request.add_header("Authorization", "Basic %s" % base64)
    
    print "ok were gonna try now"
    
    try:
        result = urllib2.urlopen(request)
    except URLError, e:
        if hasattr(e, 'reason'):
            print 'We failed to reach a server.'
            print 'Reason: ', e.reason
        elif hasattr(e, 'code'):
            print 'The server couldn\'t fulfill the request.'
            print 'Error code: ', e.code
            print e.read()
    else:
    
        response = result.read();
        result.close()
        return json.loads(response)

print upload_file(projects["projects"][0]["links"]["rootSpace"], "myFile", filepath)