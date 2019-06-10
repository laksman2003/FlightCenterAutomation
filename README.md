# FlightCenterAutomation
Automation Flight Center Web app.
Application URL - https://www.flightcentre.co.nz/

TestFlightSearch.searchFlight() -- This test method validates that Cost of flight is sorted in increasing order by default. And response time for flight search is within 3.5 Seconds.

For this Testing a normal search with Chennai to Auckland, is considered, and for this only the search results will provide only 1 Tab with list flights in increasing order of cost.

Note - For certain flight search cases like Aucklan to Chennai and others there is a AI which runs in background and gives 2 suggesstion like chapest fare and quickest route. But this Test is not automated since UI is very different. 

This is tested in chrome-74 and 75. And currently test fails since search results load time is taking more than 40 seconds.
