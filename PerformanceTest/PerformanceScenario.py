from locust import HttpUser, TaskSet, task, between


class BlazeDemoTask(TaskSet):

    @task
    def getHomePage(self):
        self.client.get(url='/index.php')

    def on_stop(self):
        pass


class Website(HttpUser):
    tasks = [BlazeDemoTask]
    host = 'https://blazedemo.com'
    wait_time = between(0.3, 0.5)