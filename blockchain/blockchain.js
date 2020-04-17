const CryptoJS = require("crypto-js");
const block = require('./block');
const vote = require('./voteClass')

class Blockchain {
    constructor() {
        this.blockChain = [this.getGenesisBlock()]
    }

    getGenesisBlock() {
        const timestamp = new Date().getTime() / 1000;
        return new block.Block(0, "0000", timestamp, "Start From ID 1")
    }

    getBlockchain() {
        return this.blockChain
    }

    getLatestBlock() {
        return this.blockChain[this.blockChain.length - 1]
    }

    generateNextBlock(voteData) {
        const previousBlock = this.getLatestBlock()
        const previousHash = previousBlock.hash
        const index = previousBlock.index + 1
        const timestamp = new Date().getTime() / 1000
        const voteDataClassObject = new vote.Vote(voteData)
        return new block.Block(index, previousHash, timestamp, voteDataClassObject)
    }

    addBlock(newBlock) {
        if (this.isValidNewBlock(this.getLatestBlock(), newBlock)) {
            this.blockChain.push(newBlock)
        }
    }

    isValidNewBlock(previousBlock, newBlock) {
        if (previousBlock.index + 1 !== newBlock.index) {
            console.log('invalid index');
            return false;
        } else if (previousBlock.hash !== newBlock.previousHash) {
            console.log('invalid previoushash');
            return false;
        } else if (newBlock.calculateHashForBlock() !== newBlock.hash) {
            console.log(typeof (newBlock.hash) + ' ' + typeof newBlock.calculateHashForBlock());
            console.log('invalid hash: ' + calculateHashForBlock(newBlock) + ' ' + newBlock.hash);
            return false;
        }
        return true;
    }
}

abc = new Blockchain()
abc.addBlock(abc.generateNextBlock("hi"))
abc.addBlock(abc.generateNextBlock("hi"))
console.log(abc.getBlockchain())

module.exports.BlockChain