//
//  OrgDetailViewController.swift
//  Swayamsewak
//
//  Created by Manish Ojha on 7/2/16.
//  Copyright © 2016 Manish Ojha. All rights reserved.
//

import UIKit
import MessageUI

class OrgDetailViewController: UIViewController, MFMailComposeViewControllerDelegate {

    @IBOutlet var name: UILabel!
    @IBOutlet var orgAdd: UILabel!
    @IBOutlet var volNum: UILabel!
    @IBOutlet var disType: UILabel!
    @IBOutlet var disAdd: UILabel!
    @IBOutlet var level: UILabel!
    
    var selectedDetail : FeedModel?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        // Set the title of the navigation bar
        title = selectedDetail!.name
        
        //Filling the detail
        name.text = selectedDetail!.name
        orgAdd.text = selectedDetail!.address
        volNum.text = selectedDetail!.volunteerNum
        disType.text = selectedDetail!.disType
        disAdd.text = selectedDetail!.area
        level.text = selectedDetail!.level
        
    }

    
    @IBAction func callButtonTapped(sender: AnyObject) {
        
        
    }
    
    
    @IBAction func emailButtonTapped(sender: AnyObject) {
 

        let defaultText = "I would like to join the volunteering program"
        
            let activityController = UIActivityViewController(activityItems: [defaultText], applicationActivities: nil)
            self.presentViewController(activityController, animated: true, completion: nil)
        
    }
  

    
    
    
    @IBAction func reportButtonTapped(sender: AnyObject) {
    
        // Send user data to the server side
        
        
        let request = NSMutableURLRequest(URL: NSURL(string: "http://localhost/Yomari/model/report.php")!)
        request.HTTPMethod = "POST"
        let postString = "id=\(selectedDetail?.id)"
        request.HTTPBody = postString.dataUsingEncoding(NSUTF8StringEncoding)
        
        
        
        let task = NSURLSession.sharedSession().dataTaskWithRequest(request) {
            data, response, error in
            
            if error != nil {
                print("error=\(error)")
                return
            }
            
            print("response = \(response)")
            
            
            
            let responseString = NSString(data: data!, encoding: NSUTF8StringEncoding)
            print("responseString = \(responseString)")
            
            dispatch_async(dispatch_get_main_queue(),{
                
                // Display alert message with confirmation
                let myAlert = UIAlertController(title: "Reported", message:"Your report has been sucesfully posted to the admin. ", preferredStyle: UIAlertControllerStyle.Alert)
                
                let okAction = UIAlertAction (title: "Proceed", style: UIAlertActionStyle.Default){
                    action in
                    self.dismissViewControllerAnimated(true, completion:nil)
                    
                }
                
                myAlert.addAction(okAction)
                self.presentViewController(myAlert, animated: true, completion: nil)
            })
            
        }
        task.resume()
        
        
    }

    
    

    
   
}
