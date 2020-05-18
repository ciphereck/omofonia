const express = require('express')
const votechainModule = require('./VoteChain')

const app = express();
const http_port = process.env.HTTP_PORT || 3001;

const votechain = new votechainModule.VoteChain()
votechain.addnewBlockChain()



const initRoutes = () => {
    app.get('/blocks', (req, res) => res.send(votechain.getVoteChain()))
    app.get('/blocks/:id', (req, res) => res.send(votechain.getSpecificBlockChain(req.params.id)))
    app.post('/vote/:id', (req, res) => res.send(votechain.addVote(req.params.id)))
    app.get('/voteCount/:id', (req, res) => res.send(votechain.countVotes(req.params.id)))
    app.get('/', (req, res) => res.send("BlockChain Running here......."))
    app.get('*', (req, res) => res.send("Route Not Found. Please request proper routes"))
}

initRoutes()

app.listen(http_port, () => console.log("Http Routes Listening on port: ", http_port))