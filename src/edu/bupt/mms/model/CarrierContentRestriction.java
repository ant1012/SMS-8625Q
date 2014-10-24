/*
 * Copyright (C) 2008 Esmertec AG.
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.bupt.mms.model;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.util.Log;

import com.google.android.mms.ContentType;
import edu.bupt.mms.ContentRestrictionException;
import edu.bupt.mms.ExceedMessageSizeException;
import edu.bupt.mms.LogTag;
import edu.bupt.mms.MmsConfig;
import edu.bupt.mms.ResolutionException;
import edu.bupt.mms.UnsupportContentTypeException;

public class CarrierContentRestriction implements ContentRestriction {
    private static final ArrayList<String> sSupportedImageTypes;
    private static final ArrayList<String> sSupportedAudioTypes;
    private static final ArrayList<String> sSupportedVideoTypes;
    private static final ArrayList<String> sSupportedTextTypes;
    private static final boolean DEBUG = true;

    static {
        sSupportedImageTypes = ContentType.getImageTypes();
        sSupportedAudioTypes = ContentType.getAudioTypes();
        sSupportedVideoTypes = ContentType.getVideoTypes();
        sSupportedTextTypes  = ContentType.getSupportedTypes();
    }

    public CarrierContentRestriction() {
    }

    public void checkMessageSize(int messageSize, int increaseSize, ContentResolver resolver)
            throws ContentRestrictionException {
        if (DEBUG) {
            Log.d(LogTag.APP, "CarrierContentRestriction.checkMessageSize messageSize: " +
                        messageSize + " increaseSize: " + increaseSize +
                        " MmsConfig.getMaxMessageSize: " + MmsConfig.getMaxMessageSize());
        }
        if ( (messageSize < 0) || (increaseSize < 0) ) {
            throw new ContentRestrictionException("Negative message size"
                    + " or increase size");
        }
        int newSize = messageSize + increaseSize;

        if ( (newSize < 0) || (newSize > MmsConfig.getMaxMessageSize()) ) {
            throw new ExceedMessageSizeException("Exceed message size limitation");
        }
    }

    public void checkResolution(int width, int height) throws ContentRestrictionException {
        if ( (width > MmsConfig.getMaxImageWidth()) || (height > MmsConfig.getMaxImageHeight()) ) {
            throw new ResolutionException("content resolution exceeds restriction.");
        }
    }

    public void checkImageContentType(String contentType)
            throws ContentRestrictionException {
        if (null == contentType) {
            throw new ContentRestrictionException("Null content type to be check");
        }

        if (!sSupportedImageTypes.contains(contentType)) {
            throw new UnsupportContentTypeException("Unsupported image content type : "
                    + contentType);
        }
    }

    public void checkAudioContentType(String contentType)
            throws ContentRestrictionException {
        if (null == contentType) {
            throw new ContentRestrictionException("Null content type to be check");
        }
//zaizhe
        if (!sSupportedAudioTypes.contains(contentType)) {
            throw new UnsupportContentTypeException("Unsupported audio content type : "
                    + contentType);
        }
    }
    
	
		/**
	 * 北邮ANT实验室
	 * qq
	 * 
	 * 加入vcard格式检查
	 * 
	 * 功能序号58，所属需求：彩信需求
	 * 	 * 
	 * */
    
    public void checkVCardContentType(String contentType)
            throws ContentRestrictionException {
        if (null == contentType) {
            throw new ContentRestrictionException("Null content type to be check");
        }
//zaizhe
//        if (!sSupportedTextTypes.contains(contentType)) {
//            throw new UnsupportContentTypeException("Unsupported audio content type : "
//                    + contentType);
//        }
    }
    
    
    

    public void checkVideoContentType(String contentType)
            throws ContentRestrictionException {
        if (null == contentType) {
            throw new ContentRestrictionException("Null content type to be check");
        }

        if (!sSupportedVideoTypes.contains(contentType)) {
            throw new UnsupportContentTypeException("Unsupported video content type : "
                    + contentType);
        }
    }
}
