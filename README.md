# TestAutomationPractise
**Pre-Requisites:**
1. Install & setup Latest version of JDK & Maven
2. Install latest version of pyhton3
3. Install locust using command `pip3 install locust`
4. Cloned this repo and navigate to root folder of this repo in Terminal / CMD

**Steps to Run the Tests:**
1. To run UI automated tests, run command `mvn -q clean test -Dtest=ECommerceUITest`
2. To run API automated tests, run command `mvn -q clean test -Dtest=PetStoreAPI`
3. To generate load on the website, navigate to `PerformanceTest` and run command `locut -f PerformanceScenario.py`

