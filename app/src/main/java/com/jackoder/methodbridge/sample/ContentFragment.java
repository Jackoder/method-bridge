package com.jackoder.methodbridge.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jackoder.methodbridge.MethodBridge;
import com.jackoder.methodbridge.ann.ExportMethod;

/**
 * @author Jackoder
 * @version 2015/12/30.
 */
public class ContentFragment extends Fragment {

    TextView mTvContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MethodBridge.call("print");
        View view = inflater.inflate(R.layout.fragment_content, null);
        mTvContent = (TextView) view.findViewById(R.id.tv_content);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String data = MethodBridge.call("ShareData");
        mTvContent.setText(data);
    }

    @ExportMethod(name = "Toast")
    public void toast(String data) {
        Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        MethodBridge.registerAnnotatedClass(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        MethodBridge.unregisterAnnotatedClass(this);
    }
}
