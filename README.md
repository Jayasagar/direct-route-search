##### Run commands:
*  ./gradlew clean build
* java -jar ./build/libs/direct-search-route-1.0.jar ./sample-data.txt
* ./service.sh start ./sample-data.txt

###### Solution approach taken points
* First thing I want to avoid duplicate stop(stations) object. Using two model object Route and Stop and they are immutable 
* On application booting, loads the data from file into the Application Data
** Validates the Data, if not exit from application

###### Things to improve
* More unit tests 
* Effective streams!
* Spring Cache on Service method so that we can improve performance

###### Bus Route Data
* 1st Line: Num of lines
* Rest of the lines are routes
* Bus Route: List of integers (Space separated) - ATLEAST 3 numbers
** 1st is route ID
** rest are station IDS
** NEVER occur in same route
** Assume -> Bus routes == 100,000 as upper limit MAX
** 1,000,000 as upper limit for the number of stations
** 1,000 as upper limit for the number of station of one bus route
* The bus route data file will be a local file
* your service will get the path to file as the first command line argument
* Your service will get restarted if the file or its location changes.
* Your micro service has to implement a REST-API a single URL and only GET requests 

###### Solution:
* STATION object should be immutable so that we only create MAX 1,000,000 stations
* Break the loop if we found direct route/service b/w 2 stations
* Cache the complete data in memory on Load or cache only user requested search data
* Use Caching solutions (Else simple Map)
* Map<Route, Set<Stations>>
* Spring boot
* Assume bus-route-data is fixed file name
* Watcher 
* Model
** Route(Id, List<Station>)
** Station(Id)

###### Tasks
* Readme 
* Spring boot project template 
* Basic initial Controller
* Basic model
* Load data on load
* Caching Spike
* Validations
* Specific Exceptions 
* Test driven development

###### Logic/Flow: quick
* map.values().stream().map(list<Stations> -> list.stream()).filter(contains(dep_is) && contains(ar_id)).limit(1).

###### Test Cases:
* Validate first line only contains 1 integer else throw Invalid Data File Exception
* Validate at least 3 integers else throw Invalid Data File Exception
* Validate Route Id is not repeated across the file else throw Invalid Data File Exception
* Validate Station Id is not repeated Within the route else throw Invalid Data File Exception
* Given user inputs should match in order: order matters

###### Keep in mind
* future changes
* 