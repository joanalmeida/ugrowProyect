window.onload = windowLoad;

function windowLoad(){
	document.getElementById("experienceText").onkeyup = countCharacters;
	countCharacters();
	document.getElementById("english").onclick = addEnglish;
	document.getElementById("french").onclick = addFrench;
	document.getElementById("german").onclick = addGerman;
	document.getElementById("italian").onclick = addItalian;
	document.getElementById("portuguese").onclick = addPortuguese;
	document.getElementById("spanish").onclick = addSpanish;
	if(document.getElementById("englishSpoken")) {
		addEnglish();
	}
	if(document.getElementById("frenchSpoken")) {
		addFrench();
	}
	if(document.getElementById("germanSpoken")) {
		addGerman();
	}
	if(document.getElementById("italianSpoken")) {
		addItalian();
	}
	if(document.getElementById("portugueseSpoken")) {
		addPortuguese();
	}
	if(document.getElementById("spanishSpoken")) {
		addSpanish();
	}
}


function countCharacters() {
	document.getElementById("charactersLeft").innerHTML = (500 - document
			.getElementById("experienceText").value.length)
			+ " characters left";
}

function addEnglish() {
	document.getElementById("englishDiv").innerHTML="<a id='englishSpoken' onclick='deleteEnglish();'>English</a><input type='hidden' name='english' value='true'/>";
}
function addFrench() {
	document.getElementById("frenchDiv").innerHTML="<a id='frenchSpoken' onclick='deleteFrench();'>French</a><input type='hidden' name='french' value='true'/>";
}
function addGerman() {
	document.getElementById("germanDiv").innerHTML="<a id='germanSpoken' onclick='deleteGerman();'>German</a><input type='hidden' name='german' value='true'/>";
}
function addItalian() {
	document.getElementById("italianDiv").innerHTML="<a id='italianSpoken' onclick='deleteItalian();'>Italian</a><input type='hidden' name='italian' value='true'/>";
}
function addPortuguese() {
	document.getElementById("portugueseDiv").innerHTML="<a id='portugueseSpoken' onclick='deletePortuguese();'>Portuguese</a><input type='hidden' name='portuguese' value='true'/>";
}
function addSpanish() {
	document.getElementById("spanishDiv").innerHTML="<a id='spanishSpoken' onclick='deleteSpanish();'>Spanish</a><input type='hidden' name='spanish' value='true'/>";
}

function deleteEnglish(){
	document.getElementById("englishDiv").innerHTML="";
}
function deleteFrench(){
	document.getElementById("frenchDiv").innerHTML="";
}
function deleteGerman(){
	document.getElementById("germanDiv").innerHTML="";
}
function deleteItalian(){
	document.getElementById("italianDiv").innerHTML="";
}
function deletePortuguese(){
	document.getElementById("portugueseDiv").innerHTML="";
}
function deleteSpanish(){
	document.getElementById("spanishDiv").innerHTML="";
}