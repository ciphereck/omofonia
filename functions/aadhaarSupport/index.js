// const parser = require('xml2json');
const convert = require('xml-js')

module.exports.giveAadhaarJSON =  function (str, json) {
	// const jsonFromXML = parser.toJson(str, {
	// 	object: true,
	// 	coerce: true,
	// });

	const jst = convert.xml2json(str, {compact: true, spaces: 4})
	jsonFromXML = JSON.parse(jst)
//	console.log(jsonFromXML.OfflinePaperlessKyc.UidData)
	const address = getAddress(jsonFromXML.OfflinePaperlessKyc.UidData.Poa._attributes)

	json.aadhaarName =  jsonFromXML.OfflinePaperlessKyc.UidData.Poi._attributes.name,
	json.dob = jsonFromXML.OfflinePaperlessKyc.UidData.Poi._attributes.dob,
	json.gender = jsonFromXML.OfflinePaperlessKyc.UidData.Poi._attributes.gender,
	json.address = address,
	json.hasedEmail =  jsonFromXML.OfflinePaperlessKyc.UidData.Poi._attributes.e,
	json.hashedMobileNo =  jsonFromXML.OfflinePaperlessKyc.UidData.Poi._attributes.m,
	json.picInJP2000 = jsonFromXML.OfflinePaperlessKyc.UidData.Pht._text
//	console.log(json)
	//console.log(jsonFromXML.OfflinePaperlessKyc.UidData.Poi)
	return json
}

function getAddress(json) {
	const firstLine = json.careof + " " + json.house + " " + json.loc + " " + json.landmark
	const secondLine = json.street + " " + json.vtc + " " + json.po + " " + json.subdist 
	const thirdLine = json.dist + " " + json.state + " " + json.country + " " + json.pc
	return firstLine + " " + secondLine + " " + thirdLine
}
