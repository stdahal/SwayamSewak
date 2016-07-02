//
//  FeedDetailModel.swift
//  Swayamsewak
//
//  Created by Manish Ojha on 7/2/16.
//  Copyright © 2016 Manish Ojha. All rights reserved.
//

import Foundation

protocol FeedDetailModelProtocal: class {
    func itemsDownloaded(items: NSArray)
}


class FeedDetailModel: NSObject, NSURLSessionDataDelegate {
    
    //properties
    
    weak var delegate: FeedDetailModelProtocal!
    
    var data : NSMutableData = NSMutableData()
    
    let urlPath: String = "http://localhost/Yomari/model/get.php"
    
    //Function to get all the requred data that is been returned from the get.php file
    
    func downloadItems() {
        
        let url: NSURL = NSURL(string: urlPath)!
        var session: NSURLSession!
        let configuration = NSURLSessionConfiguration.defaultSessionConfiguration()
        
        
        session = NSURLSession(configuration: configuration, delegate: self, delegateQueue: nil)
        
        let task = session.dataTaskWithURL(url)
        
        task.resume()
        
    }
    
    func URLSession(session: NSURLSession, dataTask: NSURLSessionDataTask, didReceiveData data: NSData) {
        self.data.appendData(data);
        
    }
    
    func URLSession(session: NSURLSession, task: NSURLSessionTask, didCompleteWithError error: NSError?) {
        if error != nil {
            print("Failed to download data")
        }else {
            print("Data downloaded")
            self.parseJSON()
        }
        
    }
    
    //function to parse the data from the database to the application
    
    func parseJSON() {
        
        var jsonResult: NSMutableArray = NSMutableArray()
        
        do{
            jsonResult = try NSJSONSerialization.JSONObjectWithData(self.data, options:NSJSONReadingOptions.AllowFragments) as! NSMutableArray
            
        } catch let error as NSError {
            print(error)
            
        }
        
        var jsonElement: NSDictionary = NSDictionary()
        let data: NSMutableArray = NSMutableArray()
        
        for(var i = 0; i < jsonResult.count; i++)
        {
            
            jsonElement = jsonResult[i] as! NSDictionary
            
            let detail = FeedModel()
            
            //the following insures none of the JsonElement values are nil through optional binding
            
            if  
                let name = jsonElement["Org_Name"] as? String,
                let address = jsonElement["Org_Address"] as? String,
                let volunteerNum = jsonElement["Num_Of_Volunteers"] as? String,
                let level = jsonElement["Emergency_Level"] as? String,
                let area = jsonElement["Disaster_Address"] as? String,
                let id = jsonElement["Id"] as? String,
                let status = jsonElement["Status"] as? String,
                let disType = jsonElement["Disaster_Type"] as? String

            
                
                
            {
                // oreganization address : address
                // disaster address : area
                
                detail.name = name
                detail.address = address
                detail.volunteerNum = volunteerNum
                detail.level = level
                detail.area = area
                detail.id = id
                detail.status = status
                detail.disType = disType
                
            }
            
            data.addObject(detail)
            
        }
        
        //passsing the above data to the detail view controller
        
        dispatch_async(dispatch_get_main_queue(), { () -> Void in
            
            self.delegate.itemsDownloaded(data)
            
        })
    }
}