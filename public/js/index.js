console.log("Hi")
console.log(config.BASE)



function getDevelopers(id) {
	console.log("developers")
	const req = axios.get(config.BASE + "/voter/info?aadhaarNo=" + id)	
	return req.then(token => {
		console.log(token.data)
		return token.data
	})
}