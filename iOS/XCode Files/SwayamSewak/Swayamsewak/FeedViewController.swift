//
//  FeedViewController.swift
//  Swayamsewak
//
//  Created by Manish Ojha on 7/1/16.
//  Copyright © 2016 Manish Ojha. All rights reserved.
//

import UIKit

class FeedViewController: UIViewController, UITableViewDataSource, UITableViewDelegate, FeedDetailModelProtocal {

    var feedItems: NSArray = NSArray()
    var selectedDetail : FeedModel = FeedModel()
    
    @IBOutlet var feedTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Remove the title of the back button
        navigationItem.backBarButtonItem = UIBarButtonItem(title: "", style: .Plain, target: nil, action: nil)
        
        //set delegates and initialize homeModel
        
        self.feedTableView.delegate = self
        self.feedTableView.dataSource = self
        
        let feedDetailModel = FeedDetailModel()
        feedDetailModel.delegate = self
        feedDetailModel.downloadItems()
        
        
    }

    func itemsDownloaded(items: NSArray) {
        
        feedItems = items
        self.feedTableView.reloadData()
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // Return the number of feed items
        return feedItems.count
        
    }

    // function to set the data of the bike in the cell
    
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        
        let cellIdentifier = "orgDetailCell"
        let cell = tableView.dequeueReusableCellWithIdentifier(cellIdentifier, forIndexPath: indexPath) as! FeedCell
        
        
        let item: FeedModel = feedItems[indexPath.row] as! FeedModel
        // Configure the cell...

        cell.name.text = item.name
        cell.address.text = item.address
        cell.volunteerNum.text = item.volunteerNum
        cell.area.text = item.area
        cell.level.text = item.level
        
        
        
        
           
        return cell

    }
    
    
    
    // MARK: - Navigation
    // Sends the data of the bike selected to the detail view controller
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == "detailSegue" {
            if let indexPath = feedTableView.indexPathForSelectedRow {
                selectedDetail = feedItems[indexPath.row] as! FeedModel
                
                let detailVC = segue.destinationViewController as! OrgDetailViewController
                detailVC.selectedDetail = selectedDetail
                
            }
        } }

}
