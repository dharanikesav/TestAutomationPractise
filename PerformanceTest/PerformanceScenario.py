from locust import HttpUser, TaskSet, task


class BlazeDemoTask(TaskSet):

    @task
    def getHomePage(self):
        self.client.get(url='/en')

    def on_stop(self):
        pass


class Website(HttpUser):
    tasks = [BlazeDemoTask]
    host = 'https://www.bitpanda.com'