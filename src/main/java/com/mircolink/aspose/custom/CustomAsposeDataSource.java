package com.mircolink.aspose.custom;

import com.aspose.words.IMailMergeDataSource;
import com.aspose.words.ref.Ref;

public class CustomAsposeDataSource implements IMailMergeDataSource {
    @Override
    public String getTableName() throws Exception {
        return null;
    }

    @Override
    public boolean moveNext() throws Exception {
        return false;
    }

    @Override
    public boolean getValue(String s, Ref<Object> ref) throws Exception {
        return false;
    }

    @Override
    public IMailMergeDataSource getChildDataSource(String s) throws Exception {
        return null;
    }
}
