function showHide(id) {

	var divelement = document.getElementById(id);
	if (divelement.style.display == 'none')
		divelement.style.display = 'block';
	else
		divelement.style.display = 'none';
}



function showHideSecRes(id) {

	var fieldSetElement = document.getElementById(id);
	if ( fieldSetElement.style.display == 'none')
		 fieldSetElement.style.display = 'block';
	     fieldSetElement.style.display = 'none';
	/*else{
		divelement.style.display = 'block';
	}*/
}

