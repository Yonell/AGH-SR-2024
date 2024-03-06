from fastapi import FastAPI
from fastapi.responses import JSONResponse
from fastapi import status

from Poll import Poll
from Vote import Vote

app=FastAPI( )

polls = []

def nextPollID():
    i = 0
    while True:
        yield i
        i += 1

def nextVoteId():
    i = 0
    while True:
        yield i
        i += 1

pollIter = nextPollID()
voteIter = nextVoteId()

# sample requests and queries
@app.get("/")
async def root() :
    return {"message" : "Hello World"}

@app.get("/poll")
async def get_polls() :
    return JSONResponse(status_code=status.HTTP_200_OK, content=[
        {
            "poll_id": i.id,
            "possible_values": i.possible_values,
        }
        for i in polls
    ])

@app.post("/poll")
async def create_poll(possibleValues: str):
    poll = Poll(next(pollIter), possibleValues.split('_'))
    polls.append(poll)
    return JSONResponse(status_code=status.HTTP_201_CREATED, content={"poll_id": poll.id, "possible_values": poll.possible_values})

@app.get("/poll/{poll_id}")
async def get_poll(poll_id:int) :
    poll_index = None
    for i in range(len(polls)):
        if polls[i].id == poll_id:
            poll_index = i
            break

    if poll_index == None:
        return JSONResponse(status_code=status.HTTP_400_BAD_REQUEST, content=None)

    return JSONResponse(status_code=status.HTTP_200_OK, content=
    {
        "poll_id": polls[poll_index].id,
        "possible_values": polls[poll_index].possible_values
    }
    )

@app.put("/poll/{poll_id}")
async def update_poll(possibleValues: str, poll_id: int) :
    poll_index = None
    for i in range(len(polls)):
        if polls[i].id == poll_id:
            poll_index = i
            break

    if poll_index == None:
        return JSONResponse(status_code=status.HTTP_400_BAD_REQUEST, content=None)

    result = polls[poll_index].updatePoll(possibleValues.split('_'))
    if result == -1:
        return JSONResponse(status_code=status.HTTP_409_CONFLICT, content=None)

    return JSONResponse(status_code=status.HTTP_202_ACCEPTED, content={"poll_id": poll_id, "possible_values": possibleValues})

@app.delete("/poll/{poll_id}")
async def delete_poll(poll_id: int) :
    poll_index = None
    for i in range(len(polls)):
        if polls[i].id == poll_id:
            poll_index = i
            break

    if poll_index is None:
        return JSONResponse(status_code=status.HTTP_400_BAD_REQUEST, content=None)

    polls.pop(poll_index)

    return JSONResponse(status_code=status.HTTP_200_OK, content=None)

@app.post("/poll/{poll_id}/vote")
async def cast_vote(poll_id: int, value: str) :
    poll_index = None
    for i in range(len(polls)):
        if polls[i].id == poll_id:
            poll_index = i
            break

    if poll_index is None:
        return JSONResponse(status_code=status.HTTP_400_BAD_REQUEST, content=None)

    vote = Vote(next(voteIter), value)
    if polls[poll_index].addVote(vote) == -1:
        return JSONResponse(status_code=status.HTTP_400_BAD_REQUEST, content=None)
    return JSONResponse(status_code=status.HTTP_201_CREATED, content={"vote_id": vote.id, "vote_value": vote.value})

@app.get("/poll/{poll_id}/vote")
async def get_votes(poll_id: int) :
    poll_index = None
    for i in range(len(polls)):
        if polls[i].id == poll_id:
            poll_index = i
            break

    if poll_index is None:
        return JSONResponse(status_code=status.HTTP_400_BAD_REQUEST, content=None)

    return JSONResponse(status_code=status.HTTP_200_OK, content=[
        {
            "vote_id": i.id,
            "value": i.value,
        }
        for i in polls[poll_index].votes
    ])

@app.get("/poll/{poll_id}/vote/{vote_id}")
async def get_vote(poll_id: int, vote_id: int) :
    poll_index = None
    for i in range(len(polls)):
        if polls[i].id == poll_id:
            poll_index = i
            break

    if poll_index is None:
        return JSONResponse(status_code=status.HTTP_400_BAD_REQUEST, content=None)

    vote_index = None

    for i in range(len(polls[poll_index].votes)):
        if polls[poll_index].votes[i].id == vote_id:
            vote_index = i
            break

    if vote_index is None:
        return JSONResponse(status_code=status.HTTP_400_BAD_REQUEST, content=None)

    return JSONResponse(status_code=status.HTTP_200_OK, content=
        {
            "vote_id": polls[poll_index].votes[vote_index].id,
            "value": polls[poll_index].votes[vote_index].value,
        }
        )

@app.put("/poll/{poll_id}/vote/{vote_id}")
async def update_vote(poll_id: int, vote_id: int, value: str) :
    poll_index = None
    for i in range(len(polls)):
        if polls[i].id == poll_id:
            poll_index = i
            break

    if poll_index is None:
        return JSONResponse(status_code=status.HTTP_400_BAD_REQUEST, content=None)

    if polls[poll_index].updateVote(vote_id, value) == -1:
        return JSONResponse(status_code=status.HTTP_400_BAD_REQUEST, content=None)

    return JSONResponse(status_code=status.HTTP_201_CREATED, content={"vote_id": vote_id, "vote_value": value})

@app.delete("/poll/{poll_id}/vote/{vote_id}")
async def delete_vote(poll_id: int, vote_id: int) :
    poll_index = None
    for i in range(len(polls)):
        if polls[i].id == poll_id:
            poll_index = i
            break

    if polls[poll_index].removeVote(vote_id) == -1:
        return JSONResponse(status_code=status.HTTP_400_BAD_REQUEST, content=None)

    return JSONResponse(status_code=status.HTTP_200_OK, content=None)

'''@app.get("/get_options/{poll_id}")
async def get_options(poll_id: int) :
    poll_index = None
    for i in range(len(polls)):
        if polls[i].id == poll_id:
            poll_index = i
            break

    if poll_index is None:
        return JSONResponse(status_code=status.HTTP_400_BAD_REQUEST, content=None)

    return JSONResponse(status_code=status.HTTP_200_OK, content=[
        {
            "value": i
        }
        for i in polls[poll_index].possible_values
    ])'''



@app.get("/poll/{poll_id}/results")
async def get_results(poll_id: int) :
    poll_index = None
    for i in range(len(polls)):
        if polls[i].id == poll_id:
            poll_index = i
            break

    if poll_index is None:
        return JSONResponse(status_code=status.HTTP_400_BAD_REQUEST, content=None)

    results = polls[poll_index].getResults()

    return JSONResponse(status_code=status.HTTP_200_OK, content=[
        {
            "value": i,
            "vote_count": results[i]
        }
        for i in polls[poll_index].possible_values
    ])




