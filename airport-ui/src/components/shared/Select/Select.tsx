import React from 'react';
import { Select as SelectBase } from 'antd';

import 'antd/lib/grid/style/index.less';

export interface Option {
    value: string | number;
    label: string | number;
}

interface Props {
    options: Option[];
    showSearch?: boolean;
    placeholder?: string;
    onChange: (value: string) => void;
    onFocus?: () => void;
    onBlur?: () => void;
    onSearch?: () => void;
}

const Select = ({ options, showSearch, placeholder, onChange, onFocus, onBlur, onSearch }: Props) => {
    const { Option } = SelectBase;

    return (
        <SelectBase
            showSearch={showSearch}
            style={{ width: 220 }}
            placeholder={placeholder}
            optionFilterProp="label"
            onChange={onChange}
            onFocus={onFocus}
            onBlur={onBlur}
            onSearch={onSearch}
            filterOption={(input, option) => {
                if (option) {
                    return option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0;
                }
                return false;
            }}
        >
            {options.map((option) => (
                <Option key={option.value} value={option.value}>
                    {option.label}
                </Option>
            ))}
        </SelectBase>
    );
};

export default Select;
