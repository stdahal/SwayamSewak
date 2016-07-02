//
//  FeedModel.swift
//  Swayamsewak
//
//  Created by Manish Ojha on 7/2/16.
//  Copyright © 2016 Manish Ojha. All rights reserved.
//

import Foundation



class FeedModel: NSObject {
    
    //properties
    
    // assigning the propoerties of the bike assigning attributes
    
    var name: String?
    var address: String?
    var volunteerNum: String?
    var level: String?
    var area: String?
    var id: String?
    var status: String?
    var disType: String?
    
    
    //empty constructor
    
    override init()
    {
        
    }
    
    //construct with @name, @address, @latitude, and @longitude parameters
    
    init(name: String, address: String, volunteerNum: String) {
        
        self.name = name
        self.address = address
        self.volunteerNum = volunteerNum
        
    }
    
    
    //    //prints object's current state
    //
    //    override var description: String {
    //        return "Bike_Name: \(name), Bike_Type: \(type), Bike_Company: \(company), Bike_Price: \(price)"
    //
    //    }
    //    
    
}