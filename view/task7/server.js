var express = require('express');
var app = express();
var path = require('path');
var session = require('express-session');
var bodyParser = require('body-parser');
var fs = require('fs');
var Db = require('mongodb').Db;
var Server = require('mongodb').Server; //user/bin
var db = new Db('tutor',
    new Server("localhost", 27017, {safe:true},
    {auto_reconnect:true}, {})
);
var ObjectID = require('mongodb').ObjectID;


db.open(function(){
    console.log("mongo opened");
})

db.collection('notes', function(error, notes){
    db.notes = notes;
});


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
   db.notes.find(req.query).toArray(function(error, items){
        res.send(items);
   })
});


app.post("/notes", function(req,res){
    console.log(req.body);
    db.notes.insert(req.body);
    res.end();
});

app.delete("/notes", function(req,res){
    var id = new ObjectID(req.query.id);
    db.notes.remove({_id:id}, function(err){
        if(err){
            console.log(err);
            res.send("Failed");
        }else{
            res.send("Success");
        }
    })
});

