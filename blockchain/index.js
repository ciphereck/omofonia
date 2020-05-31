const express = require('express')
const votechainModule = require('./VoteChain')
const bodyParser = require('body-parser')

const app = express();
const http_port = process.env.HTTP_PORT || 3001;

app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())

const votechain = new votechainModule.VoteChain()
votechain.addnewBlockChain()



const initRoutes = () => {
    app.post('/create', (req, res) => res.send({"index": votechain.addnewBlockChain()}))
    app.get('/length', (req, res) => res.send({"length": votechain.getLength()}))
    app.get('/blocks', (req, res) => res.send(votechain.getVoteChain()))
    app.get('/blocks/:id', (req, res) => res.send(votechain.getSpecificBlockChain(req.params.id)))
    app.post('/vote/:id', castVote)
    app.get('/voteCount/:id', (req, res) => res.send(votechain.countVotes(req.params.id)))
    app.get('/', (req, res) => res.send("BlockChain Running here......."))
    app.get('*', (req, res) => res.send("Route Not Found. Please request proper routes"))
}

function castVote(req, res) {
    return res.send(votechain.addVote(req.params.id, req.body.candidateId, req.body.voterId))
}

initRoutes()

app.listen(http_port, () => console.log("Http Routes Listening on port: ", http_port))