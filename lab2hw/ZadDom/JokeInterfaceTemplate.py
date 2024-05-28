from math import trunc

import requests
import random

class JoikeInterfaceTemplate():
    URLs = None
    def __init__(self):
        URLs = [

        ]
        pass

    def getAJoke(self) -> str:
        index = trunc(random.random()*len(self.URLs))
        x = requests.get(self.URLs[index])
        return x.text
