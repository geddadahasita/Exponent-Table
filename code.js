onEvent("signinButton", "click", function( ) {
	console.log("signinButton clicked!");
	setScreen("2");
});

/*
Adds the text trom the text input field "nameInput" and display the welcome screen. 
*/
onEvent("nextButton1", "click", function(event) {
  setText("name", getText("nameInput"));
  setScreen("3");
});

/*
Choose ava and display the welcome screen. 
*/
onEvent("bear", "click", function(event) {
  setImageURL("ava", "assets/Screen-Shot-2022-06-08-at-12.32.49-PM.png");
  setScreen("4");
});

onEvent("owl", "click", function(event) {
  setImageURL("ava", "assets/Screen-Shot-2022-06-08-at-12.32.55-PM.png");
  setScreen("4");
});

onEvent("deer", "click", function(event) {
  setImageURL("ava", "assets/Screen-Shot-2022-06-08-at-12.32.28-PM.png");
  setScreen("4");
});

onEvent("fox", "click", function(event) {
  setImageURL("ava", "assets/Screen-Shot-2022-06-08-at-12.33.03-PM.png");
  setScreen("4");
});

/*
Choose the topic and and go to the alphabet. 
*/
onEvent("alphabet", "click", function(event) {
  setScreen("5");
});

/*
Choose the topic and and go to the number. 
*/
onEvent("number", "click", function(event) {
  setScreen("31");
});


/*
This part dedicated to alphabet
*/

/*A*/
onEvent("prevA", "click", function( ) {
  setScreen("4");
  
});
onEvent("nextA", "click", function( ) {
  setScreen("6");
  
});
onEvent("Asound", "click", function( ) {
  playSound("assets/A.mp3", false);
});


/*B*/
onEvent("prevB", "click", function( ) {
  setScreen("5");
  
});
onEvent("nextB", "click", function( ) {
  setScreen("7");
  
});
onEvent("Bsound", "click", function( ) {
  playSound("assets/B.mp3", false);
});


/*C*/
onEvent("prevC", "click", function( ) {
  setScreen("6");
  
});
onEvent("nextC", "click", function( ) {
  setScreen("8");
  
});
onEvent("Csound", "click", function( ) {
  playSound("assets/C.mp3", false);
});

/*D*/
onEvent("prevD", "click", function( ) {
  setScreen("7");
  
});
onEvent("nextD", "click", function( ) {
  setScreen("9");
  
});
onEvent("Dsound", "click", function( ) {
  playSound("assets/D.mp3", false);
});

/*E*/
onEvent("prevE", "click", function( ) {
  setScreen("8");
  
});
onEvent("nextE", "click", function( ) {
  setScreen("10");
  
});
onEvent("Esound", "click", function( ) {
  playSound("assets/E.mp3", false);
});

/*F*/
onEvent("prevF", "click", function( ) {
  setScreen("9");
  
});
onEvent("nextF", "click", function( ) {
  setScreen("11");
  
});
onEvent("Fsound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*G*/
onEvent("prevG", "click", function( ) {
  setScreen("10");
  
});
onEvent("nextG", "click", function( ) {
  setScreen("12");
  
});
onEvent("Gsound", "click", function( ) {
  playSound("assets/default.mp3", false);
});


/*H*/
onEvent("prevH", "click", function( ) {
  setScreen("11");
  
});
onEvent("nextH", "click", function( ) {
  setScreen("13");
  
});
onEvent("Hsound", "click", function( ) {
  playSound("assets/default.mp3", false);
});


/*I*/
onEvent("prevI", "click", function( ) {
  setScreen("12");
  
});
onEvent("nextI", "click", function( ) {
  setScreen("14");
  
});
onEvent("Isound", "click", function( ) {
  playSound("assets/default.mp3", false);
});


/*J*/
onEvent("prevJ", "click", function( ) {
  setScreen("13");
  
});
onEvent("nextJ", "click", function( ) {
  setScreen("15");
  
});
onEvent("Jsound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*K*/
onEvent("prevK", "click", function( ) {
  setScreen("14");
  
});
onEvent("nextK", "click", function( ) {
  setScreen("16");
  
});
onEvent("Ksound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*L*/
onEvent("prevL", "click", function( ) {
  setScreen("15");
  
});
onEvent("nextL", "click", function( ) {
  setScreen("17");
  
});
onEvent("Lsound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*M*/
onEvent("prevM", "click", function( ) {
  setScreen("16");
  
});
onEvent("nextM", "click", function( ) {
  setScreen("18");
  
});
onEvent("Msound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*N*/
onEvent("prevN", "click", function( ) {
  setScreen("17");
  
});
onEvent("nextN", "click", function( ) {
  setScreen("19");
  
});
onEvent("Nsound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*O*/
onEvent("prevO", "click", function( ) {
  setScreen("18");
  
});
onEvent("nextO", "click", function( ) {
  setScreen("20");
  
});
onEvent("Osound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*P*/
onEvent("prevP", "click", function( ) {
  setScreen("19");
  
});
onEvent("nextP", "click", function( ) {
  setScreen("21");
  
});
onEvent("Psound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*Q*/
onEvent("prevQ", "click", function( ) {
  setScreen("20");
  
});
onEvent("nextQ", "click", function( ) {
  setScreen("22");
  
});
onEvent("Qsound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*R*/
onEvent("prevR", "click", function( ) {
  setScreen("21");
  
});
onEvent("nextR", "click", function( ) {
  setScreen("23");
  
});
onEvent("Rsound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*S*/
onEvent("prevS", "click", function( ) {
  setScreen("22");
  
});
onEvent("nextS", "click", function( ) {
  setScreen("24");
  
});
onEvent("Ssound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*T*/
onEvent("prevT", "click", function( ) {
  setScreen("23");
  
});
onEvent("nextT", "click", function( ) {
  setScreen("25");
  
});
onEvent("Tsound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*U*/
onEvent("prevU", "click", function( ) {
  setScreen("24");
  
});
onEvent("nextU", "click", function( ) {
  setScreen("26");
  
});
onEvent("Usound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*V*/
onEvent("prevV", "click", function( ) {
  setScreen("25");
  
});
onEvent("nextV", "click", function( ) {
  setScreen("27");
  
});
onEvent("Vsound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*W*/
onEvent("prevW", "click", function( ) {
  setScreen("26");
  
});
onEvent("nextW", "click", function( ) {
  setScreen("28");
  
});
onEvent("Wsound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*X*/
onEvent("prevX", "click", function( ) {
  setScreen("27");
  
});
onEvent("nextX", "click", function( ) {
  setScreen("29");
  
});
onEvent("Xsound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*Y*/
onEvent("prevY", "click", function( ) {
  setScreen("28");
  
});
onEvent("nextY", "click", function( ) {
  setScreen("30");
  
});
onEvent("Ysound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*Go back to main menu after Z*/
/*Z*/
onEvent("prevZ", "click", function( ) {
  setScreen("28");
  
});
onEvent("nextZ", "click", function( ) {
  setScreen("4");
  
});
onEvent("Zsound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*this is number*/
/*1*/
onEvent("prev1", "click", function( ) {
  setScreen("4");
  
});
onEvent("next1", "click", function( ) {
  setScreen("32");
  
});
onEvent("1sound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*2*/
onEvent("prev2", "click", function( ) {
  setScreen("31");
  
});
onEvent("next2", "click", function( ) {
  setScreen("33");
  
});
onEvent("2sound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*3*/
onEvent("prev3", "click", function( ) {
  setScreen("32");
  
});
onEvent("next3", "click", function( ) {
  setScreen("34");
  
});
onEvent("3sound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*4*/
onEvent("prev4", "click", function( ) {
  setScreen("33");
  
});
onEvent("next4", "click", function( ) {
  setScreen("35");
  
});
onEvent("4sound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*5*/
onEvent("prev5", "click", function( ) {
  setScreen("34");
  
});
onEvent("next5", "click", function( ) {
  setScreen("36");
  
});
onEvent("5sound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*6*/
onEvent("prev6", "click", function( ) {
  setScreen("35");
  
});
onEvent("next6", "click", function( ) {
  setScreen("37");
  
});
onEvent("6sound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*7*/
onEvent("prev7", "click", function( ) {
  setScreen("36");
  
});
onEvent("next7", "click", function( ) {
  setScreen("38");
  
});
onEvent("7sound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*8*/
onEvent("prev8", "click", function( ) {
  setScreen("37");
  
});
onEvent("next8", "click", function( ) {
  setScreen("39");
  
});
onEvent("8sound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*9*/
onEvent("prev9", "click", function( ) {
  setScreen("38");
  
});
onEvent("next9", "click", function( ) {
  setScreen("40");
  
});
onEvent("9sound", "click", function( ) {
  playSound("assets/default.mp3", false);
});

/*10*/
onEvent("prev10", "click", function( ) {
  setScreen("39");
  
});
onEvent("next10", "click", function( ) {
  setScreen("4");
  
});
onEvent("10sound", "click", function( ) {
  playSound("assets/default.mp3", false);
});


/*quiz1*/
onEvent("test", "click", function( ) {
	setScreen("quiz1");
});
onEvent("buttonA", "click", function() {
  setScreen("tryAgainScreen");
  playSound("assets/category_digital/power_down_1.mp3", false);
});
onEvent("buttonB", "click", function() {
  setScreen("screen2");
  playSound("assets/default.mp3", false);
});
onEvent("buttonC", "click", function() {
  setScreen("quiz2");
  
});
onEvent("buttonD", "click", function() {
  setScreen("tryAgainScreen");
  playSound("assets/category_digital/power_down_1.mp3", false);
});
onEvent("tryAgain", "click", function( ) {
	setScreen("quiz1");
});


/*quiz2*/
onEvent("f1", "click", function() {
  setScreen("tryAgainScreen2");
  playSound("assets/category_digital/power_down_1.mp3", false);
});
onEvent("f2", "click", function() {
  setScreen("correct");
  
});
onEvent("f3", "click", function() {
  setScreen("screen2");
  playSound("assets/default.mp3", false);
});
onEvent("f4", "click", function() {
  setScreen("tryAgainScreen2");
  playSound("assets/category_digital/power_down_1.mp3", false);
});
onEvent("tryAgain2", "click", function( ) {
	setScreen("quiz2");
});

onEvent("mainmenu", "click", function() {
  setScreen("4");
  
});
// /*quiz3*/
// onEvent("buttonA2", "click", function(event) {
//   setScreen("tryAgainScreen2");
//   playSound("assets/category_digital/power_down_1.mp3", false);
// });
// onEvent("buttonB2", "click", function(event) {
//   setScreen("tryAgainScreen2");
//   playSound("assets/category_digital/power_down_1.mp3", false);
// });
// onEvent("buttonC2", "click", function(event) {
//   setScreen("screen2");
//   playSound("assets/default.mp3", false);
// });
// onEvent("buttonD2", "click", function(event) {
//   setScreen("tryAgainScreen2");
//   playSound("assets/category_digital/power_down_1.mp3", false);
// });
// onEvent("button2", "click", function( ) {
// 	setScreen("screen2");
// });



