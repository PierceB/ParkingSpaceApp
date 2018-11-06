Readme for the classifier.

The ImageCropper.py and Classify.py files are just helper files, running those won't actually do anything. 

To use the Generate file you type must just include the lot_id you want to generate the pictures for. 

example:
python Generate.py 1

This wil generate pictures for all the parking bays in lot 1, but make sure the picture "snapshot.jpeg" is for lot 1.

To update specific/all lots use the Update file. 

If you include no extra arguments it will update all lots, or you can include which lots you want to update.

Example 
python Update.py (this updates all lots)

python Update.py 1 3 5 (this updates lots 1 3 5). 

Again these will work off of snapshot.jpeg. So until we know how the classifier will receive the feed it will be pretty useless to update all lots off of 1 picture.

Note: for update to run make sure you have combined the comvo_1.h5 file.

The ImageScale.py script has 2 functions in it:
strong_imageScale(image, width,shouldPrint)
  image is the image being loaded into a cv2 matrix
  width is the width that you want the resized image to be
  Set shouldPrint=1 if you want it to save the resized image
  

weak_imageScale(image, width,height, shouldPrint)
  image is the image being loaded into a cv2 matrix
  width is the width that you want the resized image to be
  height is the height you want the resized image to be
  Set shouldPrint=1 if you want it to save the resized image

The strong_imageScale will maintain the original pictures aspect ratio, while the weak_imageScale function will just set the width and height to the values its given, completely ignoring the aspect ratio.

This can be done with the terminal command:
cat comvo_1.h5.part* > comvo_1.h5
