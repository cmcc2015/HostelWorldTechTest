Requirements:
- Download: http://jmeter-plugins.org/wiki/JSONPathExtractor/ (ExtraLibs) and install to JMeter home like regular jmeter plugins
- Assume JMeter bin is on your path
- Run (Windows): jmeter.bat -n -t Gists.jmx -l output.jtl -JAuthToken=YOUR_TOKEN_HERE
- I ran out of time to get the json extractor to grab out the desired id: - and then run the http based on that but the intention is there