"use strict";

function Grade(form) {
	
	//var answersCountTotal  = form.querySelectorAll('.QueAnswerElement');
	//const list = document.querySelectorAll("input[type=checkbox]");
	
	var questionsCountTotal = form.querySelectorAll('[name^=QueBody]').length;
	var	answersCountTotal  = form.querySelectorAll('[class=QueAnswerElement]').length;

	const inputElementsColl = form.querySelectorAll('[class=QueAnswerElement]');
	
	var elementsWithCorrectAnswerTotal = getElementsWithValueCount(inputElementsColl,"1");
	var inputElementsCorrectAnswerCount = getCheckedElementsWithValueCount(inputElementsColl,"1");
	var resultsInPercent = Math.round(100 * (inputElementsCorrectAnswerCount / elementsWithCorrectAnswerTotal));
	
	confirm("The test has been graded.  You correctly answered " + resultsInPercent + "% of the questions.");

	//print results statistic
	document.open();

	document.writeln("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>");
	document.writeln("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1 plus MathML 2.0//EN\" \"http://www.w3.org/TR/MathML2/dtd/xhtml-math11-f.dtd\">");
	document.writeln("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">");
	document.writeln("<head>");
	document.writeln("<title>Results</title></head>");
	document.writeln("<body bgcolor=\"#FFFFFF\">");

	if (resultsInPercent > 0) {
	  document.writeln("<table width=\"100%\" border=\"0\"><tr><td align=\"left\">");
	  document.writeln("<font size=\"+2\"><b>" + resultsInPercent + "%</b></font> (" + inputElementsCorrectAnswerCount + " out of " + elementsWithCorrectAnswerTotal + " answers correct)<br />");
	  document.writeln("</td><td align=\"right\">");
	  document.writeln("</td></tr></table><br /><br />");
	}

	document.writeln("</body></html>");
	document.close();
}

function getInitializedAr(upperBound, initValue){
	var array = new Array(upperBound);
	for (var i = 0; i < array.length; i++) {
		array[i] = initValue;
	}
	return array;
}

function getCheckedElementsWithValueCount(elements, value){
	var res = 0;
	for (var i = 0; i < elements.length; i++) {
		if (elements[i].checked) {
			if (elements[i].value == value) {
				res++;
			}
		}
	}
	return res;
}

function getElementsWithValueCount(elements, value){
	// init empty html collection
	//const elementsCollection = document.createElement("div").getElementsByClassName('noClassHere');
	var res = 0;
	for (const element of elements) {
	  if ( element.value == value ){
		  res++;
	  }
	}
	return res;
}

function getElementsByPartNameCount(form, namePart) {
	var count = 0;
	for (var i = 0; i < form.elements.length; i++) {
		if (form.elements[i].name.substring(0, namePart.length) == namePart) { 
			count++;
		}
	}
	return count;
}

function isDocumentNeedClose() {
  var userAgent = navigator.userAgent.toLowerCase();
  var isAppleWebkit = (userAgent.indexOf("applewebkit/") != -1);
  return (!isAppleWebkit);
}