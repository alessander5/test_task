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


app.get("/sections", function(req,res){
    var userName = req.session.userName || "demo";
    var userName = req.session.userName || "demo";
    db.sections.find({userName:userName}).toArray(function(err, items) {
        res.send(items);
    });
});

app.post("/sections/replace", function(req,res) {
    var userName = req.session.userName || "demo";
    db.users.update({userName:userName}, {$set:{sections:req.body}},
        function() {
            res.end();
        });
});


db.collection('sections', function(error, sections){
    db.sections = sections;
})

/////////////


db.collection('notes', function(error, notes){
    db.notes = notes;
});

app.get("/notes", function(req,res){
    setUserQuery(req);
    var str = req.query.order;
    switch(str){
        case "date":
            db.notes.find(req.query).sort({date:1}).toArray(function(error, items){
                res.send(items);
            });
            break;
        case "text":
            db.notes.find(req.query).sort({text:1}).toArray(function(error, items){
                res.send(items);
            });
            break;
        case "order":
        default:
            db.notes.find(req.query).sort({order:1}).toArray(function(error, items){
                res.send(items);
            });
    }
});

app.post("/notes", function(req,res){
    setUserQuery(req);
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
        });
    db.notes.find({text:req.query.text})
        .toArray(function(error, items){
            var current = items[0];
            db.notes.update(current, { $set:{order: topNote.order-1} });
        });
    db.notes.find(req.query).sort({order:1}).toArray(function(error, items){
        res.send(items);
    })
});

///// users ///

db.collection('users', function(error, users) {
    db.users = users;
});

app.post("/users", function(req,res) {
    db.users.insert(req.body, function(resp) {
        req.session.userName = req.body.userName;
        res.end();
    });
});

app.get("/checkUser", function(req,res){
    db.users.findOne(req.query, function(error, user){
        res.send(Boolean(!user));
    });
});

///

function setUserQuery(req) {
    req.query.userName = req.session.userName || "demo";
}

app.post("/login", function(req,res) {
    var id = {userName:req.body.login, password:req.body.password};
    db.users.find(id).toArray(function(err, items) {
        if (items.length>0) {
            req.session.userName = req.body.login;
        }
        res.send(items.length>0);
    });
});

app.get("/logout", function(req, res) {
    req.session.userName = null;
    res.end();
});
