db = db.getSiblingDB('admin');
db.createUser({user:"infra",pwd:"infra",roles:[{role:"userAdmin",db:"infra",role:"readWrite",db:"infra"}]});
db.createUser({user:"t000000000_docgen",pwd:"t000000000_docgen",roles:[{role:"userAdmin",db:"t000000000_docgen",role:"readWrite",db:"t000000000_docgen"}]});