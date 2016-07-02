//
//  VolunteeringForm.swift
//  Swayamsewak
//
//  Created by Manish Ojha on 7/2/16.
//  Copyright © 2016 Manish Ojha. All rights reserved.
//

import UIKit

class VolunteeringForm: UIViewController {
    
    @IBOutlet var orgName: UITextField!
    @IBOutlet var orgAddress: UITextField!
    @IBOutlet var orgNumber: UITextField!
    @IBOutlet var orgEmail: UITextField!
    
    @IBOutlet var disAddress: UITextField!
    @IBOutlet var disType: UITextField!
    @IBOutlet var noOfVolunteer: UITextField!
    @IBOutlet var emergencyLevel: UITextField!
    @IBOutlet var daysOfVolunteering: UITextField!
    
    
    @IBAction func postButtonClicked(sender: AnyObject) {
        
        let name = orgName.text
        let address = orgAddress.text
        let number = orgNumber.text
        let email = orgEmail.text
        let disAdd = disAddress.text
        let disTyp = disType.text
        let volNum = noOfVolunteer.text
        let level = emergencyLevel.text
        let days = daysOfVolunteering.text
        
        
        
        // Validate input fields
        if name == "" || address == "" || number == "" || email == "" || disTyp == "" || volNum == "" || level == "" || days == "" || disAdd == "" {
            let alertController = UIAlertController(title: "Oops", message: "We can't proceed because fields are blank. Please note that all fields are required to post.", preferredStyle: UIAlertControllerStyle.Alert)
            alertController.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.Default, handler: nil))
            self.presentViewController(alertController, animated: true, completion: nil)
            
            return
        }
        else
        {
        
            // Send user data to the server side
            
            let request = NSMutableURLRequest(URL: NSURL(string: "http://localhost/Yomari/model/insert.php")!)
            request.HTTPMethod = "POST"
            let postString = "name=\(orgName.text!) & address=\(orgAddress.text!) & number=\(orgNumber.text!) & email=\(orgEmail.text!) & disAdd=\(disAddress.text!) & disType=\(disType.text!) & volNumber=\(noOfVolunteer.text!) & level=\(emergencyLevel.text!) & volDays=\(daysOfVolunteering.text!)"
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
                    let myAlert = UIAlertController(title: "Sucessfull", message:"Thank You \( name!). Your AD has been sucesfully posted. ", preferredStyle: UIAlertControllerStyle.Alert)
                    
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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
   
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
}

