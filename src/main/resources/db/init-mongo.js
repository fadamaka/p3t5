db.createUser(
  {
    user  : "admin"
    pwd   : "admin"
    roles : [
      {
        role  : "readWrite",
        db    : "p3t5-db"
      }
    ]
  }
)