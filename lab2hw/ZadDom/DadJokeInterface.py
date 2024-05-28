from math import trunc

import requests
import random

class DadJokeInterface():
    URLs = None
    def __init__(self):
        URLs = [
            "https://icanhazdadjoke.com/"
        ]
        pass

    def getAJoke(self) -> str:
        index = trunc(random.random()*len(self.URLs))
        x = requests.get(self.URLs[index], headers={
            "Accept": "text/plain"
        })
        return x.text
