//
//  Database.swift
//  RecipeSelector
//
//  Created by Артем on 24/12/2019.
//  Copyright © 2019 ArtemTkachuk. All rights reserved.
//

import Foundation
import SQLite

class Database {
static let shared = Database()
public let connection: Connection?
private init(){
do {
let dbPath = Bundle.main.path(forResource: "recipes", ofType: "db")!

connection = try Connection(dbPath, readonly: true)
} catch {
connection = nil
let nserror = error as NSError
print ("Cannot connect to Database. Error is: \(nserror), \(nserror.userInfo)")
}
}
}

