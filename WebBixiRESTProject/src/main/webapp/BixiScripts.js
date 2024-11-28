const rightBody = document.getElementById("rightBody");
const leftBody = document.getElementById("leftBody");
const login = document.getElementById("login");
const loggedin = document.getElementById("loggedin");
const user = document.getElementById("user");
const services = document.getElementById("services");
const selectDetails = document.getElementById("selectDetails");
const selectOrigin =  document.getElementById("selectOrigin");
const selectDest =  document.getElementById("selectDest");
const username =  document.getElementById("username");
const password =  document.getElementById("password");
const userSpace =  document.getElementById("userSpace");
const invalid =  document.getElementById("invalid");
var stationID;
var originID;
var destID;
selectDetails.addEventListener("change", (e) => {stationID = e.target.value;});
selectOrigin.addEventListener("change", (e) => {originID = e.target.value;});
selectDest.addEventListener("change", (e) => {destID = e.target.value;});
getStationMenus("stationID");
getStationMenus("originID");
getStationMenus("destID");
clearRightBody();

async function loginProcedure() {
	invalid.innerHTML = "";
	userSpace.innerHTML = "";
	await getUserCheck();
	if (invalid.innerHTML != "Invalid login" && invalid.innerHTML != "Unsuccessful login") {
		login.style.display = 'none';
		loggedin.style.display = 'block';
		user.style.display = 'none';
		services.style.display = 'block';
	}
}
function logoutProcedure() {
	invalid.innerHTML = "";
	userSpace.innerHTML = "";
	username.value = "";
	password.value = "";
	login.style.display = 'block';
	loggedin.style.display = 'none';
	user.style.display = 'none';
	services.style.display = 'block';
}
function servicesProcedure() {
	user.style.display = 'none';
	services.style.display = 'block';
}
function accountProcedure() {
	user.style.display = 'block';
	services.style.display = 'none';
}
function clearLeftBody() {leftBody.innerHTML = "";};
async function getUserCheck() {
	var u = username.value;
	var p = password.value;
	var url = "http://localhost:8080/WebBixiRESTProject/rest/WebBixi/UserCheck/?username=" + u + "&password=" + p;
	await fetch(url)
		.then(response => {
			if (!response.ok) {throw new Error("HTTP error " + response.status);}
			return response.text();
		})
		.then(text => {
			if (text != "") userSpace.innerHTML = text;
			else invalid.innerHTML = "Invalid login";
		})
		.catch(error => {invalid.innerHTML = "Unsuccessful login";});
	window.scrollTo(0, 0);
};
async function clearRightBody() {
	await fetch('http://localhost:8080/WebBixiRESTProject/rest/WebBixi/StationList')
		.then(response => {
			if (!response.ok) {throw new Error("HTTP error " + response.status);}
			return response.text();
		})
		.then(text => {rightBody.innerHTML = text;})
		.catch(error => {rightBody.innerHTML = error;});
};
async function getStationMenus(id) {
	var url = "http://localhost:8080/WebBixiRESTProject/rest/WebBixi/StationMenu/" + id;
	await fetch(url)
		.then(response => {
			if (!response.ok) {throw new Error("HTTP error " + response.status);}
			return response.text();
		})
		.then(text => {if (id == "stationID") selectDetails.innerHTML = text;
			else if (id == "originID") selectOrigin.innerHTML = text;
			else if (id == "destID") selectDest.innerHTML = text;
		})
		.catch(error => {if (id == "stationID") selectDetails.innerHTML = error;
			else if (id == "originID") selectOrigin.innerHTML = error;
			else if (id == "destID") selectDest.innerHTML = error;
		});
};
function getStationDetails() {
	getStationDetailsID(stationID);
};
async function getStationDetailsID(id) {
	var url = "http://localhost:8080/WebBixiRESTProject/rest/WebBixi/DetailsRequest/" + id;
	await fetch(url)
		.then(response => {
			if (!response.ok) {throw new Error("HTTP error " + response.status);}
			return response.text();
		})
		.then(text => {leftBody.innerHTML = text;})
		.catch(error => {leftBody.innerHTML = error;});
	window.scrollTo(0, 0);
};
async function getDistance() {
	var url = "http://localhost:8080/WebBixiRESTProject/rest/WebBixi/DynamicDist/?station1=" + originID + "&station2=" + destID;
	await fetch(url)
		.then(response => {
			if (!response.ok) {throw new Error("HTTP error " + response.status);}
			return response.text();
		})
		.then(text => {leftBody.innerHTML = text;})
		.catch(error => {leftBody.innerHTML = error;});
	window.scrollTo(0, 0);
};
async function getRadius() {
	var latIn = document.getElementById("lat");
	var lonIn = document.getElementById("lon");
	var radIn = document.getElementById("rad");
	var lat = latIn.value;
	var lon = lonIn.value;
	var rad = radIn.value;
	var url = "http://localhost:8080/WebBixiRESTProject/rest/WebBixi/DynamicRad/?lat=" + lat + "&lon=" + lon + "&rad=" + rad;
	await fetch(url)
		.then(response => {
			if (!response.ok) {throw new Error("HTTP error " + response.status);}
			return response.text();
		})
		.then(text => {rightBody.innerHTML = text;})
		.catch(error => {rightBody.innerHTML = error;});
	window.scrollTo(0, 0);
};
