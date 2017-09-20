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
app.listen(3002);


app.get("/notes", function(req,res){
    var str = req.query.order;
    switch(str){
        case "date":
            db.notes.find().sort({date:1}).toArray(function(error, items){
                res.send(items);
            });
            break;
        case "text":
            db.notes.find().sort({text:1}).toArray(function(error, items){
                res.send(items);
            });
            break;
        case "order":
        default:
            db.notes.find().sort({order:1}).toArray(function(error, items){
                res.send(items);
            });
    }
});

app.post("/notes", function(req,res){
    db.notes.insert(req.body);
    res.end();
});

app.put("/notes", function(req,res){
    var id = new ObjectID(req.query.id);
    console.log(id);
    db.notes.find({_id:id})
        .toArray(function(error, items){
            console.log(items);
        });
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

app.get("/notes/setOnTop", function(req,res){
    var topNote;
    db.notes.find().sort({order:1}).limit(1)
        .toArray(function(error, items){
            topNote = items[0];
            console.log(topNote);
        });
    console.log(topNote);
    db.notes.find({text:req.query.text})
        .toArray(function(error, items){
            var current = items[0];
            console.log(current.text);
            db.notes.update(current, { $set:{order: topNote.order-1} });
        });
    db.notes.find(req.query).sort({order:1}).toArray(function(error, items){
        res.send(items);
    })
});

