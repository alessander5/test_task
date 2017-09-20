var express = require('express');
var app = express();
var path = require('path');
var session = require('express-session');
var bodyParser = require('body-parser');
var fs = require('fs');
app.use(express.static(path.join(__dirname,'public')));
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());
app.use(session({
            secret: 'angular_tutorial',
            resave: true,
            saveUninitialized: true
        }))
app.listen(3001);


app.get("/notes", function(req,res){
   fs.readFile("notes.json", function(err, result){
        if(result){
            result = "" + result;
            result = result.substring(0, result.length-1);
            result = "[" +result+"]";
            result = result.split("\n").join(",");
            res.send(result);
        } else{
            res.end();
        }
   })
});


app.post("/notes", function(req,res){
    console.log(req.body);
    var note = req.body;
    var noteText = JSON.stringify(note) + "\n";
    fs.appendFile("notes.json", noteText, function(err){
        if(err)
            console.log("someth go wrong");
        res.end();
    });
});
/*
app.delete("/notes", function(req,res){
    var id = req.query.id;
    var notes = req.session.notes || [];
    var updatedNotesList = [];
    for(var i=0; i<notes.length; i++){
        if(notes[i].id != id){
            updatedNotesList.push(notes[i]);
        }
    }
    req.session.notes = updatedNotesList;
    res.end();
});

app.get("/notes/sendTop", function(req,res){
    var id = req.query.id;
    var notes = req.session.notes || [];
    var updatedNotesList = [];
    for(var i=0; i<notes.length; i++){
        if(notes[i].id == id){
            updatedNotesList.push(notes[i]);
        }
    }
    for(var i=0; i<notes.length; i++){
        if(notes[i].id != id){
            updatedNotesList.push(notes[i]);
        }
    }
    req.session.notes = updatedNotesList;
    res.end();
});
*/

